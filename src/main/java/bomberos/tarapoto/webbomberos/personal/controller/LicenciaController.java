package bomberos.tarapoto.webbomberos.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bomberos.tarapoto.webbomberos.personal.model.LicenciaSuspendido;
import bomberos.tarapoto.webbomberos.personal.model.Personal;
import bomberos.tarapoto.webbomberos.personal.service.LicenciaSuspendidoService;
import bomberos.tarapoto.webbomberos.personal.service.PersonalService;

import java.util.List;

@Controller
@RequestMapping("/intranet/personal/estado")
public class LicenciaController {

    @Autowired
    private LicenciaSuspendidoService licenciaService;

    @Autowired
    private PersonalService personalService;

    // Listar bomberos con licencias activas
    @GetMapping
    public String listarBomberosConLicencias(Model model) {
        List<Personal> bomberos = personalService.listarActivosConLicencias();
        model.addAttribute("bomberos", bomberos);
        return "intranet/Personal/estado/listarLic";
    }

    // Mostrar formulario para registrar una NUEVA licencia, con datos del bombero
    // pero campos vacíos
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaLicencia(@RequestParam(required = false) String dni, Model model) {
        if (dni != null && !dni.isEmpty()) {
            Personal personal = personalService.buscarPorDniActivo(dni);
            if (personal != null) {
                LicenciaSuspendido nuevaLicencia = new LicenciaSuspendido();
                nuevaLicencia.setPersonal(personal); // solo asociamos al bombero

                model.addAttribute("licencia", nuevaLicencia);
            } else {
                model.addAttribute("error", "No se encontró bombero con DNI: " + dni);
            }
        } else {
            model.addAttribute("error", "Debe ingresar un DNI válido.");
        }

        return "intranet/Personal/estado/formLicencia"; // mismo formulario
    }

    // Formulario para ingresar nueva licencia o editar estado
    @GetMapping("/editar")
    public String mostrarFormularioEdicion(@RequestParam(required = false) String dni, Model model) {
        if (dni != null && !dni.isEmpty()) {
            Personal personal = personalService.buscarPorDniActivo(dni);
            if (personal != null) {
                // Buscar última licencia activa
                List<LicenciaSuspendido> licencias = licenciaService.listarPorPersonal(personal.getIdPersonal());
                LicenciaSuspendido licencia;

                if (!licencias.isEmpty()) {
                    licencia = licencias.get(0); // Cargar la más reciente
                } else {
                    licencia = new LicenciaSuspendido();
                }

                licencia.setPersonal(personal);
                model.addAttribute("licencia", licencia);
                model.addAttribute("licencias", licencias); // opcional
            } else {
                model.addAttribute("error", "No se encontró bombero con DNI: " + dni);
                model.addAttribute("licencia", new LicenciaSuspendido()); // <- AÑADIR ESTA LÍNEA para que el formulario
                                                                          // no falle
            }
        } else {
            model.addAttribute("licencia", new LicenciaSuspendido()); // <- AÑADIR ESTA LÍNEA para mostrar formulario
                                                                      // aunque no haya búsqueda
        }

        return "intranet/Personal/estado/formLicencia";
    }

    // Guardar estado + licencia
    @PostMapping("/guardar")
    public String guardarLicencia(@ModelAttribute LicenciaSuspendido licencia, Model model) {
        // Validar si se seleccionó un bombero
        if (licencia.getPersonal() == null || licencia.getPersonal().getIdPersonal() == null) {
            model.addAttribute("error", "Debe seleccionar un bombero antes de guardar.");
            model.addAttribute("licencia", new LicenciaSuspendido()); // Para mantener el formulario visible
            return "intranet/Personal/estado/formLicencia";
        }

        // Obtener el bombero real desde la BD
        Personal personalActual = personalService.obtenerPorId(licencia.getPersonal().getIdPersonal())
                .orElseThrow(() -> new RuntimeException("Bombero no encontrado"));

        // Actualizar estado
        personalActual.setEstado(licencia.getPersonal().getEstado());
        personalService.guardar(personalActual);

        // Guardar solo si se ingresaron datos de licencia
        if (licencia.getTipo() != null && !licencia.getTipo().isEmpty()) {
            licencia.setPersonal(personalActual);
            licencia.setDesactivado("0");
            licenciaService.guardar(licencia);
        }

        return "redirect:/intranet/personal/estado";
    }

    // Eliminar (borrado lógico)
    @GetMapping("/eliminar/{idLicencia}")
    public String eliminarLicencia(@PathVariable Integer idLicencia) {
        licenciaService.desactivarLicencia(idLicencia);
        return "redirect:/intranet/personal/estado";
    }

}
