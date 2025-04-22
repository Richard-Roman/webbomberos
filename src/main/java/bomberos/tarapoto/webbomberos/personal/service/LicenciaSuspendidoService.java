package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.ILicenciaSuspendidoDAO;
import bomberos.tarapoto.webbomberos.personal.model.LicenciaSuspendido;

@Service
public class LicenciaSuspendidoService {

    @Autowired
    private ILicenciaSuspendidoDAO licenciaDAO;

    // Listar todas las licencias activas para un personal
    public List<LicenciaSuspendido> listarPorPersonal(Integer idPersonal) {
        return licenciaDAO.findByPersonalIdPersonal(idPersonal);
    }

    // Guardar o actualizar una licencia
    public LicenciaSuspendido guardar(LicenciaSuspendido licencia) {
        return licenciaDAO.save(licencia);
    }

    // Obtener licencia por ID
    public LicenciaSuspendido obtenerPorId(Integer id) {
        return licenciaDAO.findById(id).orElse(null);
    }

    // Eliminar (borrado lógico)
    public void desactivarLicencia(Integer idLicencia) {
        Optional<LicenciaSuspendido> opt = licenciaDAO.findById(idLicencia);
        if (opt.isPresent()) {
            LicenciaSuspendido licencia = opt.get();
            licencia.setDesactivado("1");
            licenciaDAO.save(licencia);
        }
    }

    // Contar licencias activas de un bombero
    public int contarLicenciasActivasPorBombero(Integer idPersonal) {
        return licenciaDAO.countByPersonalIdPersonalAndDesactivado(idPersonal, "0");
    }

}
