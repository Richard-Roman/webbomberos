package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.HistorialCambio;

public interface IHistorialCambioDAO extends JpaRepository<HistorialCambio, Integer> {
    List<HistorialCambio> findByTablaAfectadaAndIdRegistroAfectado(String tabla, Integer id);
}
