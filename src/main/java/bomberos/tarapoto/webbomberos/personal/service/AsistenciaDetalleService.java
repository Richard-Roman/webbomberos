package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.IAsistenciaDetalleDAO;
import bomberos.tarapoto.webbomberos.personal.model.AsistenciaDetalle;

@Service
public class AsistenciaDetalleService {

    @Autowired
    private IAsistenciaDetalleDAO dao;

    public List<AsistenciaDetalle> obtenerPorRegistro(Integer idRegistro) {
        return dao.findByIdRegistroAndDesactivado(idRegistro, "0");
    }

    public Optional<AsistenciaDetalle> obtenerPorRegistroYPersonal(Integer idRegistro, Integer idPersonal) {
        return dao.findByIdRegistroAndIdPersonal(idRegistro, idPersonal);
    }

    public AsistenciaDetalle guardar(AsistenciaDetalle detalle) {
        return dao.save(detalle);
    }

    public void eliminar(Integer id) {
        Optional<AsistenciaDetalle> detalle = dao.findById(id);
        if (detalle.isPresent()) {
            detalle.get().setDesactivado("1");
            dao.save(detalle.get());
        }
    }
}