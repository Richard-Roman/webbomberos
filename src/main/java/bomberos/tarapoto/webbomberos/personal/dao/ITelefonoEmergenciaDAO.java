package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.TelefonoEmergencia;

public interface ITelefonoEmergenciaDAO extends JpaRepository<TelefonoEmergencia, Integer> {
    List<TelefonoEmergencia> findByPersonalIdPersonal(Integer idPersonal);
}