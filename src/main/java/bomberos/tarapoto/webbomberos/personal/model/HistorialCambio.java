package bomberos.tarapoto.webbomberos.personal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_cambios")
@SQLDelete(sql = "UPDATE personal SET desactivado = '1' WHERE id_personal = ?")
@Where(clause = "desactivado = '0'")
public class HistorialCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;

    private String tablaAfectada;
    private Integer idRegistroAfectado;
    private String accion;
    private LocalDateTime fechaCambio;
    private String usuario;
    private String cambiosRealizados;
    private String desactivado;

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getTablaAfectada() {
        return tablaAfectada;
    }

    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }

    public Integer getIdRegistroAfectado() {
        return idRegistroAfectado;
    }

    public void setIdRegistroAfectado(Integer idRegistroAfectado) {
        this.idRegistroAfectado = idRegistroAfectado;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCambiosRealizados() {
        return cambiosRealizados;
    }

    public void setCambiosRealizados(String cambiosRealizados) {
        this.cambiosRealizados = cambiosRealizados;
    }

    public String isDesactivado() {
        return desactivado;
    }

    public void setDesactivado(String desactivado) {
        this.desactivado = desactivado;
    }

    @Override
    public String toString() {
        return "HistorialCambio [idHistorial=" + idHistorial + ", tablaAfectada=" + tablaAfectada
                + ", idRegistroAfectado=" + idRegistroAfectado + ", accion=" + accion + ", fechaCambio=" + fechaCambio
                + ", usuario=" + usuario + ", cambiosRealizados=" + cambiosRealizados + ", desactivado=" + desactivado
                + "]";
    }

}
