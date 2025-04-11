package bomberos.tarapoto.webbomberos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.model.Usuarios;
import bomberos.tarapoto.webbomberos.model.dao.IGUsuariosDAO;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private IGUsuariosDAO usuariosDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        
        Usuarios usuario = usuariosDAO.findUsuariosByusername(username)
            .orElseThrow(()-> new UsernameNotFoundException("El  Usuario"+ username + "NO existe"));
        
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuario.getRoles()
            .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        usuario.getRoles().stream()
            .flatMap(role -> role.getPermisos().stream())
            .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

        return new User(usuario.getUsername(),
            usuario.getPassword(),
            usuario.isEnabled(),
            usuario.isAccountNonExpired(),
            usuario.isCredentialsNonExpired(),
            usuario.isAccountNonLocked(),
            authorityList);
    }
}
