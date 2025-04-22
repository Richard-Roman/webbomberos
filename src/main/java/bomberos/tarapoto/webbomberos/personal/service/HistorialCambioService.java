package bomberos.tarapoto.webbomberos.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.IHistorialCambioDAO;
import bomberos.tarapoto.webbomberos.personal.model.HistorialCambio;

@Service
public class HistorialCambioService {

    @Autowired
    private IHistorialCambioDAO historialCambioDAO;

    public List<HistorialCambio> listarPorTablaEId(String tabla, Integer idRegistro) {
        return historialCambioDAO.findByTablaAfectadaAndIdRegistroAfectado(tabla, idRegistro);
    }

    public HistorialCambio guardar(HistorialCambio historial) {
        return historialCambioDAO.save(historial);
    }

    public void eliminar(Integer id) {
        historialCambioDAO.deleteById(id);
    }
}
