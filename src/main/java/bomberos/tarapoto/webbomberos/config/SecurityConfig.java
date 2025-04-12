package bomberos.tarapoto.webbomberos.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import bomberos.tarapoto.webbomberos.Jwt.JwtAuthenticationFilter;
import bomberos.tarapoto.webbomberos.service.UserDetailServiceImpl;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor


public class SecurityConfig {

    
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    /* 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(http -> {
                //Configurate endpoint publicos
                http.requestMatchers(HttpMethod.GET,"/webbomberos/intranet/login").permitAll();
                
                //Configurate endpoint privados                
                http.requestMatchers(HttpMethod.GET,"/webbomberos/intranet/Usuarios/Visualizar_Usuarios").hasAuthority("CREATE");

                //Configurate endpoint publicos

                http.anyRequest().denyAll();
            })
            .build();
    }
    */
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
                authRequest.requestMatchers("/webbomberos/intranet/usuarios/verusuarios").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/usuarios/ucreate").hasAnyRole("ADMIN", "DEVELOPER");
                authRequest.requestMatchers("/webbomberos/intranet/usuarios/ueditar/{id}").hasAnyRole("ADMIN", "DEVELOPER");

                
                // Configurar el resto de endpoint - NO ESPECIFICADOS
                authRequest.anyRequest().denyAll();
            })
            .build();
    }

 /* 
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }


    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
*/
    
}


