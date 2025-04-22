package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.AsistenciaRegistro;

public interface IAsistenciaRegistroDAO extends JpaRepository<AsistenciaRegistro, Integer> {
    Optional<AsistenciaRegistro> findByEstadoAndDesactivado(String estado, String desactivado);

    List<AsistenciaRegistro> findByDesactivado(String desactivado);
}