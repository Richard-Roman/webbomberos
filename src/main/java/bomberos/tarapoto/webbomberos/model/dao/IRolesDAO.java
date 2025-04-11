package bomberos.tarapoto.webbomberos.model.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


import bomberos.tarapoto.webbomberos.model.Roles;
import bomberos.tarapoto.webbomberos.model.RoleEnum;

public interface IRolesDAO extends JpaRepository<Roles, Long> {
    Set<Roles> findByRoleEnumIn(Set<RoleEnum> roleEnums);
}