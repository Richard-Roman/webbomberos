package bomberos.tarapoto.webbomberos.seguridad.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bomberos.tarapoto.webbomberos.seguridad.model.Usuarios;


public interface IGUsuariosDAO extends JpaRepository<Usuarios, Integer>{

    Optional<Usuarios> findUsuariosByusername(String username);

    @Query("SELECT u FROM Usuarios u JOIN FETCH u.personal")
    List<Usuarios> findAllWithPersonal();





}
