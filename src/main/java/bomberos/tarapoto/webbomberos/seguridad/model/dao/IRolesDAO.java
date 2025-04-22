package bomberos.tarapoto.webbomberos.seguridad.model.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


import bomberos.tarapoto.webbomberos.seguridad.model.Roles;
import bomberos.tarapoto.webbomberos.seguridad.model.RoleEnum;

public interface IRolesDAO extends JpaRepository<Roles, Long> {
    Set<Roles> findByRoleEnumIn(Set<RoleEnum> roleEnums);
}