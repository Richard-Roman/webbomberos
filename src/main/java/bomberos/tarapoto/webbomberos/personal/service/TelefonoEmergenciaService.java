package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.ITelefonoEmergenciaDAO;
import bomberos.tarapoto.webbomberos.personal.model.TelefonoEmergencia;

@Service
public class TelefonoEmergenciaService {

    @Autowired
    private ITelefonoEmergenciaDAO telefonoDAO;

    public List<TelefonoEmergencia> listarPorPersonal(Integer idPersonal) {
        return telefonoDAO.findByPersonalIdPersonal(idPersonal);
    }

    public TelefonoEmergencia guardar(TelefonoEmergencia telefono) {
        return telefonoDAO.save(telefono);
    }

    public void eliminar(Integer id) {
        telefonoDAO.deleteById(id);
    }
}
