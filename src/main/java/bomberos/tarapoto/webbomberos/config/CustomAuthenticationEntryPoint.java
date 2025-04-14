package bomberos.tarapoto.webbomberos.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, 
                       HttpServletResponse response, 
                       AuthenticationException authException) throws IOException {
        
        // Redirigir a login cuando hay error de autenticación
        response.sendRedirect(request.getContextPath() + "/auth/vlogin");
    }
}