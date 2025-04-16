package bomberos.tarapoto.webbomberos.seguridad.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webbomberos/intranet")
public class MainController {

    @GetMapping(value = "main")
    public String main(Model model) {
    // Obtener el objeto de autenticación que previamente fue establecido por el filtro JWT
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Existe objeto de autenticación del usuario");
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {  // <-- CORRECTO
                System.out.println("Los detalles del usuario son válidos");
                User userDetails = (User) principal;  // <-- CORRECTO
                // Extrae el nombre de usuario y lo añade al modelo
                model.addAttribute("username", userDetails.getUsername());
                System.out.println(userDetails.getUsername());
                // Extrae los roles 
                List<String> roles = userDetails.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .filter(auth -> auth.startsWith("ROLE_"))   // Solo los que empiezan con ROLE_
                                                .map(auth -> auth.substring(5))              
                                                .collect(Collectors.toList());
                model.addAttribute("roles", roles);
                System.out.println(roles);
                System.out.println("Datos del usuario extraídos correctamente");
            }
        }
        return "intranet/main";
    }
    
}
