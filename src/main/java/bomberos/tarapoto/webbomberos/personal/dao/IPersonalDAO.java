package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.Personal;

public interface IPersonalDAO extends JpaRepository<Personal, Integer> {
    Optional<Personal> findByDni(String dni);

    List<Personal> findAll();

    Personal findByDniAndDesactivado(String dni, String desactivado);

    List<Personal> findByDesactivado(String desactivado);

}
