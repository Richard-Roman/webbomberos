package bomberos.tarapoto.webbomberos.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bomberos.tarapoto.webbomberos.personal.model.LicenciaSuspendido;

public interface ILicenciaSuspendidoDAO extends JpaRepository<LicenciaSuspendido, Integer> {
    List<LicenciaSuspendido> findByPersonalIdPersonal(Integer idPersonal);

    List<LicenciaSuspendido> findByPersonalIdPersonalAndDesactivado(Integer idPersonal, String desactivado);

    int countByPersonalIdPersonalAndDesactivado(Integer idPersonal, String desactivado);

}
