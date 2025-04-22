package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.IEstudioDAO;
import bomberos.tarapoto.webbomberos.personal.model.Estudio;

@Service
public class EstudioService {

    @Autowired
    private IEstudioDAO estudioDAO;

    public List<Estudio> listarPorPersonal(Integer idPersonal) {
        return estudioDAO.findByPersonalIdPersonalAndRegistroActivo(idPersonal, "1");
    }

    public Estudio guardar(Estudio estudio) {
        return estudioDAO.save(estudio);
    }

    public Optional<Estudio> obtenerPorId(Integer id) {
        return estudioDAO.findById(id);
    }

    public void eliminar(Integer id) {
        estudioDAO.deleteById(id);
    }
}
