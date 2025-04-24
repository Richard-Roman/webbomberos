package bomberos.tarapoto.webbomberos.seguridad.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bomberos.tarapoto.webbomberos.personal.model.Personal;

import java.util.Collection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")


public class Usuarios implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer Id_Usuario;

    @Column(name = "nombre_usuario", unique = true)
    private String username;
    @Column(name = "contrasena")
    private String password;
    @Column(name = "email")
    private String email;

    //Cambiar a realcio onetoone con personal

    // -- Relación con Personal --
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_personal", referencedColumnName = "idPersonal")
    private Personal personal;
    @Column(name = "is_Enabled")
    private Boolean isEnabled;
    @Column(name = "account_NO_Expired")
    private Boolean accountNoExpired;
    @Column(name = "account_NO_Locked")
    private Boolean accountNoLocked;
    @Column(name = "credential_No_Expired")
    private Boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name="id_rol"))
    private Set<Roles> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()));
            for (Permisos permiso : role.getPermisos()) {
                authorities.add(new SimpleGrantedAuthority(permiso.getNombre_permiso()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public Set<Roles> getRoles(){
        return roles;
    }



    @Override
    public boolean isAccountNonExpired() {
        return accountNoExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNoLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNoExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}