package bomberos.tarapoto.webbomberos.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.model.Usuarios;


public interface IGUsuariosDAO extends JpaRepository<Usuarios, Integer>{

    Optional<Usuarios> findUsuariosByusername(String username);





}
