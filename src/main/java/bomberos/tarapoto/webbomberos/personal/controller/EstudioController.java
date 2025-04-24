package bomberos.tarapoto.webbomberos.personal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bomberos.tarapoto.webbomberos.personal.model.Estudio;
import bomberos.tarapoto.webbomberos.personal.model.Personal;
import bomberos.tarapoto.webbomberos.personal.service.EstudioService;
import bomberos.tarapoto.webbomberos.personal.service.PersonalService;

@Controller
@RequestMapping("intranet/personal/estudio")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @Autowired
    private PersonalService personalService;

    // Mostrar lista de estudios de bomberos activos@GetMapping
    @GetMapping
    public String listarEstudios(Model model) {
        // Obtener todos los bomberos activos
        List<Personal> bomberosActivos = personalService.listarActivos();

        // Filtrar solo los que tienen al menos un estudio activo
        List<Personal> bomberosConEstudios = bomberosActivos.stream()
                .filter(b -> !estudioService.listarPorPersonal(b.getIdPersonal()).isEmpty())
                .collect(Collectors.toList());
        
        System.out.println(bomberosActivos.toString());
        System.out.println(bomberosConEstudios.toString());

        model.addAttribute("bomberos", bomberosConEstudios);
        return "intranet/Personal/estudio/listarEstu";
    }

    // Mostrar formulario para registrar nuevo estudio
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@RequestParam(required = false) String dni, Model model) {
        if (dni != null && !dni.isEmpty()) {
            Personal personal = personalService.buscarPorDniActivo(dni);
            if (personal != null) {
                Estudio estudio = new Estudio();
                estudio.setPersonal(personal);

                model.addAttribute("estudio", estudio);
            } else {
                model.addAttribute("error", "No se encontró bombero con DNI: " + dni);
                model.addAttribute("estudio", new Estudio());
            }
        } else {
            model.addAttribute("error", "Debe ingresar un DNI válido.");
            model.addAttribute("estudio", new Estudio());
        }

        return "intranet/Personal/estudio/formEstudio";
    }

    // Mostrar formulario para editar estudio o ver historial
    @GetMapping("/editar")
    public String editarEstudio(@RequestParam(required = false) String dni,
            @RequestParam(required = false) Integer idEstudio,
            Model model) {
        if (dni != null && !dni.isEmpty()) {
            Personal personal = personalService.buscarPorDniActivo(dni);
            if (personal != null) {
                Estudio estudio;

                if (idEstudio != null) {
                    // Estamos editando un estudio existente
                    estudio = estudioService.obtenerPorId(idEstudio).orElse(new Estudio());
                } else {
                    // Estamos creando un nuevo estudio para este bombero
                    List<Estudio> estudios = estudioService.listarPorPersonal(personal.getIdPersonal());
                    estudio = estudios.isEmpty() ? new Estudio() : estudios.get(0);
                }

                estudio.setPersonal(personal);
                model.addAttribute("estudio", estudio);
                model.addAttribute("estudios", estudioService.listarPorPersonal(personal.getIdPersonal()));
            } else {
                model.addAttribute("error", "No se encontró bombero con DNI: " + dni);
                model.addAttribute("estudio", new Estudio());
            }
        } else {
            model.addAttribute("estudio", new Estudio());
        }

        return "intranet/Personal/estudio/formEstudio";
    }

    // Guardar estudio
    @PostMapping("/guardar")
    public String guardarEstudio(@ModelAttribute Estudio estudio, Model model) {
        if (estudio.getPersonal() == null || estudio.getPersonal().getIdPersonal() == null) {
            model.addAttribute("error", "Debe seleccionar un bombero.");
            model.addAttribute("estudio", new Estudio());
            return "intranet/Personal/estudio/formEstudio";
        }

        Personal personalActual = personalService.obtenerPorId(estudio.getPersonal().getIdPersonal())
                .orElseThrow(() -> new RuntimeException("Bombero no encontrado"));

        estudio.setPersonal(personalActual);
        estudio.setDesactivado("0");
        estudioService.guardar(estudio);

        return "redirect:/intranet/personal/estudio";
    }

    // Eliminar estudio (lógico, si configuras la entidad correctamente)
    @GetMapping("/eliminar/{idEstudio}")
    public String eliminar(@PathVariable Integer idEstudio) {
        Estudio estudio = estudioService.obtenerPorId(idEstudio).orElse(null);
        if (estudio != null) {

            estudio.setDesactivado("1");
            estudioService.guardar(estudio); // borrado lógico
        }
        return "redirect:/intranet/personal/estudio";
    }
}
