package bomberos.tarapoto.webbomberos.personal.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bomberos.tarapoto.webbomberos.personal.dao.IPersonalDAO;
import bomberos.tarapoto.webbomberos.personal.model.Personal;

@Service
public class PersonalService {

    @Autowired
    private IPersonalDAO personalDAO;
    @Autowired
    private LicenciaSuspendidoService licenciaService;
    @Autowired
    private EstudioService estudioService;

    public List<Personal> listarActivosConLicencias() {
        List<Personal> bomberosActivos = personalDAO.findByDesactivado("0");
        return bomberosActivos.stream()
                .filter(b -> !b.getLicencias().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Personal> listarTodos() {
        return personalDAO.findAll();
    }

    public List<Personal> listarActivos() {
        return personalDAO.findByDesactivado("0");

    }

    public Optional<Personal> obtenerPorId(Integer id) {
        return personalDAO.findById(id);
    }

    public boolean validarDniNoDuplicado(String dni, Integer idPersonalActual) {
        // Buscar un personal con ese DNI y que esté ACTIVO
        Personal existente = personalDAO.findByDniAndDesactivado(dni, "0");

        // Si no existe, no hay duplicado
        if (existente == null) {
            return true;
        }

        // Si estamos editando, y el encontrado es el mismo, todo bien
        if (idPersonalActual != null && existente.getIdPersonal().equals(idPersonalActual)) {
            return true;
        }

        // Si existe otro activo con ese DNI, es duplicado
        return false;
    }

    public boolean validarDniNoDuplicado(String dni) {
        // Buscar un personal con el mismo DNI y que esté activo (desactivado = "0")
        Optional<Personal> personalExistente = personalDAO.findByDni(dni);
        // Si el personal existe y está activo, no permitir el guardado
        return personalExistente.isEmpty() || personalExistente.get().isDesactivado().equals("1");
    }

    // Guardar un nuevo personal
    public Personal guardar(Personal personal) {
        if (personal.getTelefonosEmergencia() != null) {
            personal.getTelefonosEmergencia().forEach(t -> {
                t.setPersonal(personal);
                if (t.getDesactivado() == null) {
                    t.setDesactivado("0"); // por defecto, activos
                }
            });
        }

        // No tocamos los estudios aquí, ya que solo queremos preservar su estado
        return personalDAO.save(personal);
    }

    public void eliminar(Integer id) {
        Optional<Personal> optional = personalDAO.findById(id);
        if (optional.isPresent()) {
            Personal personal = optional.get();
            personal.setDesactivado("1");

            // Desactivar todos los teléfonos de emergencia
            if (personal.getTelefonosEmergencia() != null) {
                personal.getTelefonosEmergencia().forEach(t -> t.setDesactivado("1"));
            }

            // Desactivar todas las licencias del bombero
            if (personal.getLicencias() != null) {
                personal.getLicencias().forEach(licencia -> {
                    licencia.setDesactivado("1");
                    licenciaService.guardar(licencia);
                });
            }

            // Desactivar todos los estudios del bombero
            if (personal.getEstudios() != null) {
                personal.getEstudios().forEach(estudio -> {
                    estudio.setDesactivado("1");
                    estudioService.guardar(estudio); // Necesitarás inyectar EstudioService
                });
            }

            personalDAO.save(personal);
        }
    }

    public Optional<Personal> buscarPorDni(String dni) {
        return personalDAO.findByDni(dni);
    }

    public Personal buscarPorDniActivo(String dni) {
        return personalDAO.findByDniAndDesactivado(dni, "0");
    }

}
