package bomberos.tarapoto.webbomberos.seguridad.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.seguridad.Jwt.JwtService;
import bomberos.tarapoto.webbomberos.seguridad.model.Usuarios;
import bomberos.tarapoto.webbomberos.seguridad.model.dao.IGUsuariosDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private IGUsuariosDAO igUsuariosDAO;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        //Genera un Authentication no autenticado o ya autenticado por authenticate?
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        //si ya se autentico anter por se busca los detaller de usuario en la bd
        UserDetails user=igUsuariosDAO.findUsuariosByusername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        //aca se genera el tocken
        String token=jwtService.getToken(user);
        
        System.out.println("🔐 Token generado: " + token);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuarios usuario = Usuarios.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .build(); 
        
        igUsuariosDAO.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }

}
