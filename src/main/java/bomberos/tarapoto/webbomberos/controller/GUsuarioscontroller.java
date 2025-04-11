package bomberos.tarapoto.webbomberos.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import bomberos.tarapoto.webbomberos.model.RegisterUserRequest;
import bomberos.tarapoto.webbomberos.model.Usuarios;
import bomberos.tarapoto.webbomberos.model.dao.IGUsuariosDAO;
import bomberos.tarapoto.webbomberos.model.dao.IRolesDAO;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
//@RestController
@Controller
@RequestMapping("/webbomberos/intranet")
public class GUsuarioscontroller {
    @Autowired
    private IGUsuariosDAO usuariosDAO;
    @Autowired
    private IRolesDAO rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "main")
    public String main() {
        return "intranet/main";
    }

    @PostMapping(value = "main")
    public String welcome() {
        return "Bienvenido al Main";
    }
    
    @PostMapping(value = "usuarios/verusuarios")
    public List<Usuarios> Usuarios() {
        return usuariosDAO.findAll(); // Devuelve los datos como JSON 
    }
    @PostMapping(value = "usuarios/ucreate")
    public ResponseEntity<Usuarios> guardar(@RequestBody RegisterUserRequest usuario) {
        Usuarios nuevousuario = Usuarios.builder()
            .username(usuario.getUsername())
            .password(passwordEncoder.encode(usuario.getPassword()))
            .roles(usuario.getRolEntities(rolesRepository)) // <-- Aquí pasas el DAO
            .build(); 
        usuariosDAO.save(nuevousuario);
        return ResponseEntity.ok(nuevousuario); // Retorna el usuario creado como JSON
    }

    @GetMapping(value = "usuarios/ueditar/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuarios> usuarioid = usuariosDAO.findById(id);
        if (usuarioid.isPresent()) {
            return ResponseEntity.ok(usuarioid.get()); // Retorna el usuario en formato JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PutMapping("/intranet/Usuarios/vuupdate/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        Optional<Usuarios> usuarioExistente = usuariosDAO.findById(id);
    
        if (!usuarioExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    
        usuario.setId_Usuario(id); // Asegurar que el usuario tenga el mismo ID
        Usuarios usuarioActualizado = usuariosDAO.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
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
