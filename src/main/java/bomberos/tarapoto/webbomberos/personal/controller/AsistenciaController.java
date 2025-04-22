package bomberos.tarapoto.webbomberos.personal.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bomberos.tarapoto.webbomberos.personal.model.AsistenciaDetalle;
import bomberos.tarapoto.webbomberos.personal.model.AsistenciaRegistro;
import bomberos.tarapoto.webbomberos.personal.model.Personal;
import bomberos.tarapoto.webbomberos.personal.service.AsistenciaDetalleService;
import bomberos.tarapoto.webbomberos.personal.service.AsistenciaRegistroService;
import bomberos.tarapoto.webbomberos.personal.service.PersonalService;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaRegistroService registroService;

    @Autowired
    private AsistenciaDetalleService detalleService;

    @Autowired
    private PersonalService personalService;

    // Página principal de asistencia
    @GetMapping
    public String index(Model model) {
        List<AsistenciaRegistro> registros = registroService.listarTodos();
        model.addAttribute("registros", registros);
        return "asistencia/index";
    }

    // Mostrar formulario para nuevo registro
    @GetMapping("/nuevo-registro")
    public String mostrarFormularioNuevoRegistro(Model model) {
        AsistenciaRegistro registro = new AsistenciaRegistro();
        registro.setFecha(LocalDate.now());
        model.addAttribute("registro", registro);
        return "asistencia/nuevo-registro";
    }

    @PostMapping("/guardar-registro")
    public String guardarRegistro(
            @ModelAttribute("registro") AsistenciaRegistro registro,
            BindingResult result,
            @RequestParam("horaInicio") String horaInicioStr,
            @RequestParam("horaFin") String horaFinStr) {

        boolean esNuevo = (registro.getIdRegistro() == null);

        if (registro.getFecha() == null) {
            result.rejectValue("fecha", "fecha.requerida", "La fecha es requerida");
            return "asistencia/nuevo-registro";
        }

        try {
            LocalTime horaInicio = horaInicioStr.isEmpty() ? LocalTime.now() : LocalTime.parse(horaInicioStr);
            LocalTime horaFin = horaFinStr.isEmpty() ? LocalTime.now().plusHours(1) : LocalTime.parse(horaFinStr);

            registro.setHoraInicio(LocalDateTime.of(registro.getFecha(), horaInicio));
            registro.setHoraFin(LocalDateTime.of(registro.getFecha(), horaFin));

            if (esNuevo) {
                registro.setEstado("Abierto");
                registro.setDesactivado("0");
            }

            registro = registroService.guardar(registro); // Actualiza el objeto con ID asignado

            if (esNuevo) {
                List<Personal> bomberos = personalService.listarTodos();
                for (Personal bombero : bomberos) {
                    AsistenciaDetalle detalle = new AsistenciaDetalle();
                    detalle.setIdRegistro(registro.getIdRegistro());
                    detalle.setIdPersonal(bombero.getIdPersonal());
                    detalle.setEstadoAsistencia("Ausente");
                    detalle.setDesactivado("1");
                    detalleService.guardar(detalle);
                }
            }

            return "redirect:/asistencia";
        } catch (DateTimeParseException e) {
            result.rejectValue("horaInicio", "formato.hora.invalido", "Formato de hora inválido (HH:mm)");
            return "asistencia/nuevo-registro";
        }
    }

    // Editar registro existente
    @GetMapping("/editar/{id}")
    public String editarRegistro(@PathVariable Integer id, Model model) {
        Optional<AsistenciaRegistro> registroOpt = registroService.obtenerPorId(id);

        if (registroOpt.isPresent()) {
            AsistenciaRegistro registro = registroOpt.get();

            // Asegurarse de que la fecha esté establecida
            if (registro.getFecha() == null) {
                registro.setFecha(LocalDate.now());
            }

            // Mantener el valor de desactivado en "0"
            if (registro.getDesactivado() == null) {
                registro.setDesactivado("0");
            }

            model.addAttribute("registro", registro);

            // Pasar las horas como atributos separados para el formulario
            model.addAttribute("horaInicioForm",
                    registro.getHoraInicio() != null ? registro.getHoraInicio().toLocalTime().toString() : "");
            model.addAttribute("horaFinForm",
                    registro.getHoraFin() != null ? registro.getHoraFin().toLocalTime().toString() : "");

            return "asistencia/nuevo-registro";
        }

        return "redirect:/asistencia";
    }

    // Eliminar (desactivar) registro
    @PostMapping("/eliminar/{id}")
    public String eliminarRegistro(@PathVariable Integer id) {
        Optional<AsistenciaRegistro> registroOpt = registroService.obtenerPorId(id);

        if (registroOpt.isPresent()) {
            AsistenciaRegistro registro = registroOpt.get();
            registro.setDesactivado("1");

            // Desactivar también todos los detalles de asistencia asociados
            List<AsistenciaDetalle> detalles = detalleService.obtenerPorRegistro(id);
            for (AsistenciaDetalle detalle : detalles) {
                detalle.setDesactivado("1");
                detalleService.guardar(detalle);
            }

            registroService.guardar(registro);
        }

        return "redirect:/asistencia";
    }

    // Marcar asistencia de un bombero
    @PostMapping("/marcar-asistencia/{idRegistro}/{idPersonal}")
    public String marcarAsistencia(@PathVariable Integer idRegistro,
            @PathVariable Integer idPersonal) {

        Optional<AsistenciaRegistro> registroOpt = registroService.obtenerPorId(idRegistro);
        Optional<Personal> personalOpt = personalService.obtenerPorId(idPersonal);

        if (registroOpt.isPresent() && personalOpt.isPresent()) {
            AsistenciaRegistro registro = registroOpt.get();

            // Verificar si el registro está abierto
            if ("Abierto".equals(registro.getEstado()) &&
                    LocalDateTime.now().isBefore(registro.getHoraFin())) {

                AsistenciaDetalle detalle = detalleService.obtenerPorRegistroYPersonal(idRegistro, idPersonal)
                        .orElseGet(() -> {
                            AsistenciaDetalle nuevo = new AsistenciaDetalle();
                            nuevo.setIdRegistro(idRegistro);
                            nuevo.setIdPersonal(idPersonal);
                            nuevo.setEstadoAsistencia("Ausente");
                            nuevo.setDesactivado("0");
                            return nuevo;
                        });

                detalle.setEstadoAsistencia("Presente");
                detalle.setHoraLlegada(LocalDateTime.now());
                detalle.setHoraSalida(null); // Resetear hora de salida si estaba marcado

                detalleService.guardar(detalle);
            }
        }

        return "redirect:/asistencia/registro/" + idRegistro;
    }

    // Marcar salida de un bombero
    @PostMapping("/marcar-salida/{idRegistro}/{idPersonal}")
    public String marcarSalida(@PathVariable Integer idRegistro,
            @PathVariable Integer idPersonal) {

        Optional<AsistenciaDetalle> detalleOpt = detalleService.obtenerPorRegistroYPersonal(idRegistro, idPersonal);

        if (detalleOpt.isPresent()) {
            AsistenciaDetalle detalle = detalleOpt.get();
            detalle.setHoraSalida(LocalDateTime.now());
            detalle.setEstadoAsistencia("Ausente"); // Cambiar a Ausente al salir
            detalleService.guardar(detalle);
        }

        return "redirect:/asistencia/registro/" + idRegistro;
    }

    // Ver detalles de un registro específico
    @GetMapping("/registro/{id}")
    public String verRegistro(@PathVariable Integer id, Model model) {
        Optional<AsistenciaRegistro> registroOpt = registroService.obtenerPorId(id);

        if (registroOpt.isPresent()) {
            AsistenciaRegistro registro = registroOpt.get();
            List<AsistenciaDetalle> detalles = detalleService.obtenerPorRegistro(id);

            // Verificar y actualizar estado del registro si es necesario
            if ("Abierto".equals(registro.getEstado()) && LocalDateTime.now().isAfter(registro.getHoraFin())) {
                registro.setEstado("Cerrado");
                registroService.guardar(registro);
            }

            model.addAttribute("registro", registro);
            model.addAttribute("detalles", detalles);
            model.addAttribute("bomberos", personalService.listarTodos());
            return "asistencia/detalle";
        }

        return "redirect:/asistencia";
    }

    // Cerrar un registro manualmente
    @PostMapping("/cerrar-registro/{id}")
    public String cerrarRegistro(@PathVariable Integer id) {
        Optional<AsistenciaRegistro> registroOpt = registroService.obtenerPorId(id);

        if (registroOpt.isPresent()) {
            AsistenciaRegistro registro = registroOpt.get();
            registro.setEstado("Cerrado");
            registroService.guardar(registro);
        }

        return "redirect:/asistencia/registro/" + id;
    }
}