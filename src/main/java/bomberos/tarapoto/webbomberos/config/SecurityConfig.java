package bomberos.tarapoto.webbomberos.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import bomberos.tarapoto.webbomberos.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> 
               csrf.disable())
            .sessionManagement(sessionManager->
               sessionManager 
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authRequest -> {

                // Configurar los endpoints publicos
                authRequest.requestMatchers("/auth/**","/css/**", "/js/**", "/img/**").permitAll();

                // Configurar los endpoints privados
                authRequest.requestMatchers("/webbomberos/intranet/main").authenticated();
                authRequest.requestMatchers("/webbomberos/intranet/perfil").hasAnyRole("USER");
                authRequest.requestMatchers("/webbomberos/intranet/Usuarios/vusers").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/Usuarios/registraruser").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/Usuarios/visualizaruser/{id}").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/Usuarios/editaruser/{id}").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/Usuarios/formusers").hasAnyRole("ADMIN", "DEVELOPER");

                
                // Configurar el resto de endpoint - NO ESPECIFICADOS
                authRequest.anyRequest().denyAll();
            })
            .build();
    }
    
}


