package bomberos.tarapoto.webbomberos.Jwt;

import java.io.IOException;

import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
//import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response,       
                                    FilterChain filterChain)
            throws ServletException, IOException {
            
        try {        
            final String tockend = getTokenFromRequest(request);
            final String username;

            if(tockend==null){
                filterChain.doFilter(request, response);
                return;
            }

            username=jwtService.getUsernameFromToken(tockend);

            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(tockend, userDetails))
                {
                    UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }  else {
                    // Token inválido: Redirigir a login
                    redirectToLogin(request, response);
                    return;
                }
            }
        } catch (JwtException | AuthenticationException e) {
            // Cualquier error de JWT o autenticación: Redirigir
            redirectToLogin(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
    
    private String getTokenFromRequest(HttpServletRequest request){
        // Busca el token en las cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue(); // Devuelve el valor del token si lo encuentra
                }
            }
        }
        return null; // Si no encuentra la cookie con el token, devuelve null
    }

    private void redirectToLogin(HttpServletRequest request, 
                                 HttpServletResponse response) throws IOException {
        // Limpiar cookies si es necesario
        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
        // Redirigir a login
        response.sendRedirect(request.getContextPath() + "/auth/vlogin");
    }

}
