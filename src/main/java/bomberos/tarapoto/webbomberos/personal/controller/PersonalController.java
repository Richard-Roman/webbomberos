package bomberos.tarapoto.webbomberos.personal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bomberos.tarapoto.webbomberos.personal.model.Personal;
import bomberos.tarapoto.webbomberos.personal.dao.IPersonalDAO;
import bomberos.tarapoto.webbomberos.personal.service.PersonalService;

@Controller
@RequestMapping("/webbomberos/intranet/Personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;
    @Autowired
    private IPersonalDAO personalDAO;

    @GetMapping(value = "listar")
    public String listar(Model model) {
        List<Personal> listaPersonal = personalDAO.findAll();
        model.addAttribute("listaPersonal", listaPersonal);
        return "intranet/Personal/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("personal", new Personal());
        return "bombero/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Personal> personal = personalService.obtenerPorId(id);
        if (personal.isPresent()) {
            model.addAttribute("personal", personal.get());
            return "bombero/formulario";
        }
        return "redirect:/personal/bombero";
    }

    @PostMapping("/guardar")
    public String guardarPersonal(@ModelAttribute("personal") Personal personal, Model model) {
        personal.setDesactivado("0");

        boolean esNuevo = (personal.getIdPersonal() == null);
        boolean dniValido = personalService.validarDniNoDuplicado(personal.getDni(), personal.getIdPersonal());

        if (!dniValido) {
            model.addAttribute("error", "El DNI ya está en uso por otro registro activo.");
            model.addAttribute("personal", personal);
            return "bombero/formulario";
        }

        if (!esNuevo) {
            // Cargar el bombero existente
            Optional<Personal> existenteOpt = personalService.obtenerPorId(personal.getIdPersonal());
            if (existenteOpt.isPresent()) {
                Personal existente = existenteOpt.get();

                // Preservar licencias y teléfonos
                personal.setLicencias(existente.getLicencias());
                personal.setTelefonosEmergencia(existente.getTelefonosEmergencia());

                // Asegurar que los estudios mantengan su estado desactivado
                if (personal.getEstudios() != null) {
                    personal.getEstudios().forEach(estudio -> {
                        estudio.setPersonal(personal); // Mantener la relación
                        // No modificamos el campo desactivado
                    });
                }
            }
        }

        personalService.guardar(personal);
        return "redirect:/personal/bombero";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        personalService.eliminar(id);
        return "redirect:/personal/bombero";
    }
}
