package bomberos.tarapoto.webbomberos.seguridad.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

//@RestController
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
        
    @GetMapping(value = "vlogin")
    public String Login() {
        return "auth/login"; // debe existir login.html o login.jsp
    }

    @PostMapping(value = "login")
    public String verificaconLogin(@ModelAttribute LoginRequest request,
                                    HttpServletResponse response,
                                    RedirectAttributes redirectAttributes) {
        try {
            // 1. Autenticar y obtener token
            System.out.println("=== INICIO DE AUTENTICACIÓN ===");
            System.out.println("username"+request.getUsername());
            System.out.println("password"+request.getPassword());


            AuthResponse authResponse = authService.login(request);
            System.out.println("🔄 Token recibido en el controlador: " + authResponse.getToken());

            // 2. Crear cookie segura
            Cookie jwtCookie = new Cookie("JWT_TOKEN", authResponse.getToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true); // Solo en producción con HTTPS
            jwtCookie.setPath("/webbomberos/intranet");
            jwtCookie.setMaxAge(86400); // 1 día en segundos
            response.addCookie(jwtCookie);
            System.out.println("🍪 Cookie creada exitosamente");
            
            // 3. Redirigir a vista protegida
            return "redirect:/intranet/main";
        } catch (BadCredentialsException | DisabledException | LockedException e) {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/auth/vlogin?error=1";
        }
    }

    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request, 
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {
        
        // 1. Eliminar la cookie JWT
        Cookie jwtCookie = new Cookie("JWT_TOKEN", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true); // Mismo valor que en el login
        jwtCookie.setPath("/webbomberos/intranet");
        jwtCookie.setMaxAge(0); // Eliminar inmediatamente
        response.addCookie(jwtCookie);
        
        // 2. Invalidar la sesión actual
        SecurityContextHolder.clearContext();
        
        // 3. Opcional: Invalidar la sesión HTTP
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // 4. Redirigir con mensaje de éxito
        redirectAttributes.addFlashAttribute("message", "Sesión cerrada correctamente");
        return "redirect:/webbomberos/auth/vlogin";
    }




    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)   
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
