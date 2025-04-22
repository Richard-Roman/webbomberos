package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.Estudio;

public interface IEstudioDAO extends JpaRepository<Estudio, Integer> {
    List<Estudio> findByPersonalIdPersonalAndRegistroActivo(Integer idPersonal, String registroActivo);
}