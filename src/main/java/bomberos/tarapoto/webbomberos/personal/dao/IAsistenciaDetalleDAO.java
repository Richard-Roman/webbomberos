package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.AsistenciaDetalle;

public interface IAsistenciaDetalleDAO extends JpaRepository<AsistenciaDetalle, Integer> {
    List<AsistenciaDetalle> findByIdRegistroAndDesactivado(Integer idRegistro, String desactivado);

    Optional<AsistenciaDetalle> findByIdRegistroAndIdPersonal(Integer idRegistro, Integer idPersonalo);
}
