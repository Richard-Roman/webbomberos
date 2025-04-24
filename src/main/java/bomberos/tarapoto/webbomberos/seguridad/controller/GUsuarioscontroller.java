package bomberos.tarapoto.webbomberos.seguridad.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bomberos.tarapoto.webbomberos.seguridad.model.RegisterUserRequest;
import bomberos.tarapoto.webbomberos.seguridad.model.Usuarios;
import bomberos.tarapoto.webbomberos.seguridad.model.Roles;
import bomberos.tarapoto.webbomberos.seguridad.model.dao.IGUsuariosDAO;
import bomberos.tarapoto.webbomberos.seguridad.model.dao.IRolesDAO;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/intranet/Usuarios")
public class GUsuarioscontroller {
    @Autowired
    private IGUsuariosDAO usuariosDAO;
    @Autowired
    private IRolesDAO rolesDao;
    private final PasswordEncoder passwordEncoder;

 
    
    @GetMapping(value = "vusers")
    public String Usuarios(Model model) {
        List<Roles> listaroles = rolesDao.findAll();
        List<Usuarios> lista = usuariosDAO.findAll();
        model.addAttribute("listaroles", listaroles);
        model.addAttribute("lista", lista);
        return "intranet/Usuarios/vusers";
    }
    
@PostMapping(value = "registraruser")
public String registraruser(@ModelAttribute RegisterUserRequest usuario, RedirectAttributes redirectAttributes) {
    Usuarios nuevousuario = Usuarios.builder()
        .username(usuario.getUsername())
        .password(passwordEncoder.encode(usuario.getPassword()))
        .email(usuario.getEmail())
        .roles(usuario.getRolEntities(rolesDao)) // Asigna roles desde el formulario
        .isEnabled(usuario.getIsEnabled())
        .accountNoLocked(usuario.getAccountNoLocked())
        .accountNoExpired(true)
        .credentialNoExpired(true)
        .build(); 
    usuariosDAO.save(nuevousuario);
    // Mensaje flash para usarlo si deseas mostrar en la vista
    redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado con éxito");
    // Redirige a la vista lista de usuarios, por ejemplo
    return "redirect:/intranet/Usuarios/vusers";
    }

    @GetMapping(value = "visualizaruser/{id}")
    public String visualizaruser(@PathVariable Integer id, Model model) {
        Optional<Usuarios> usuarioid = usuariosDAO.findById(id);
        Usuarios usuario = usuarioid.get();
        List<Roles> listaroles = rolesDao.findAll();
        model.addAttribute("usuario", usuario);   
        model.addAttribute("listaroles", listaroles);
        model.addAttribute("modo", "visualizar"); 
        return "intranet/Usuarios/formusers :: visualizarForm";
    }

    @GetMapping(value = "editaruser/{id}")
    public String editaruser(@PathVariable Integer id, Model model) {
        Optional<Usuarios> usuarioid = usuariosDAO.findById(id);
        Usuarios usuario = usuarioid.get();
        List<Roles> listaroles = rolesDao.findAll();
        model.addAttribute("usuario", usuario);   
        model.addAttribute("listaroles", listaroles);
        model.addAttribute("modo", "editar");
        return "intranet/Usuarios/formusers :: editarForm";
    }

    @PostMapping("editaruser/{id}")
    public String actualizarUser(
            @PathVariable Integer id,
            @ModelAttribute RegisterUserRequest usuario,
            RedirectAttributes redirectAttributes) {
    
        Optional<Usuarios> opt = usuariosDAO.findById(id);
        if (!opt.isPresent()) {
            // Usuario no existe: volvemos al listado con mensaje de error
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/intranet/Usuarios/vusers";
        }
        // Tomamos la entidad existente y modificamos sus campos
        Usuarios existente = opt.get();
        existente.setUsername(usuario.getUsername());
        existente.setEmail(usuario.getEmail());
        existente.setRoles(usuario.getRolEntities(rolesDao));
        existente.setIsEnabled(usuario.getIsEnabled());
        existente.setAccountNoLocked(usuario.getAccountNoLocked());
        existente.setAccountNoExpired(true);
        existente.setCredentialNoExpired(true);

        if (usuario.getPassword() != null && !usuario.getPassword().trim().isEmpty()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
    
        usuariosDAO.save(existente);
    
        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado con éxito");
        return "redirect:/intranet/Usuarios/vusers";
    }
    
    


    @DeleteMapping("/intranet/Usuarios/ueliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        if (usuariosDAO.existsById(id)) {
            usuariosDAO.deleteById(id);
            return ResponseEntity.ok().body("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

}
