package bomberos.tarapoto.webbomberos.seguridad.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bomberos.tarapoto.webbomberos.seguridad.model.dao.IRolesDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {

    private String username;
    private String password;
    private String email;
    private Boolean isEnabled;
    private Boolean accountNoLocked;
    private List<String> roles;
    private Integer id_usuarios;

public Set<Roles> getRolEntities(IRolesDAO rolesRepository) {
    Set<RoleEnum> roleEnums = roles.stream()
        .map(RoleEnum::valueOf)
        .collect(Collectors.toSet());
    
    Set<Roles> foundRoles = rolesRepository.findByRoleEnumIn(roleEnums);
    
    if (foundRoles.size() != roleEnums.size()) {
        Set<RoleEnum> foundEnums = foundRoles.stream()
            .map(Roles::getRoleEnum)
            .collect(Collectors.toSet());
        
        Set<String> missing = roleEnums.stream()
            .filter(e -> !foundEnums.contains(e))
            .map(Enum::name)
            .collect(Collectors.toSet());
        
        throw new IllegalArgumentException("Roles no existen: " + missing);
    }
    return foundRoles;
    }
}






