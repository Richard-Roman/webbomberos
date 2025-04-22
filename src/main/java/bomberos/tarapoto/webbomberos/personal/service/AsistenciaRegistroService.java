package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.IAsistenciaRegistroDAO;
import bomberos.tarapoto.webbomberos.personal.model.AsistenciaRegistro;

@Service
public class AsistenciaRegistroService {

    @Autowired
    private IAsistenciaRegistroDAO dao;

    public List<AsistenciaRegistro> listarTodos() {
        return dao.findAll();
    }

    public Optional<AsistenciaRegistro> obtenerPorId(Integer id) {
        return dao.findById(id);
    }

    public AsistenciaRegistro guardar(AsistenciaRegistro registro) {
        return dao.save(registro);
    }

    public void eliminar(Integer id) {
        Optional<AsistenciaRegistro> registro = dao.findById(id);
        if (registro.isPresent()) {
            registro.get().setDesactivado("1");
            dao.save(registro.get());
        }
    }

    public Optional<AsistenciaRegistro> obtenerRegistroAbierto() {
        return dao.findByEstadoAndDesactivado("Abierto", "0");
    }

    public List<AsistenciaRegistro> listarActivos() {
        return dao.findByDesactivado("0");
    }
}