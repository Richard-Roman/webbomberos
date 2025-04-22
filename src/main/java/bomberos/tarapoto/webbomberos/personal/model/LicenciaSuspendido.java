package bomberos.tarapoto.webbomberos.personal.model;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "licencias_suspendidos")
@SQLDelete(sql = "UPDATE licencias_suspendidos SET desactivado = '1' WHERE id_licencia = ?")
@Where(clause = "desactivado = '0'")

public class LicenciaSuspendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLicencia;

    @ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;

    private String tipo;
    private String descripcion;
    private String nombreDocumento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagenDocumento;
    @Column(length = 1)
    private String desactivado;

    public Integer getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getImagenDocumento() {
        return imagenDocumento;
    }

    public void setImagenDocumento(String imagenDocumento) {
        this.imagenDocumento = imagenDocumento;
    }

    public String isDesactivado() {
        return desactivado;
    }

    public String getDesactivado() {
        return desactivado;
    }

    public void setDesactivado(String desactivado) {
        this.desactivado = desactivado;
    }

    @Override
    public String toString() {
        return "LicenciaSuspendido [idLicencia=" + idLicencia + ", personal=" + personal + ", tipo=" + tipo
                + ", descripcion=" + descripcion + ", nombreDocumento=" + nombreDocumento + ", fechaInicio="
                + fechaInicio + ", fechaFin=" + fechaFin + ", imagenDocumento=" + imagenDocumento + ", desactivado="
                + desactivado + "]";
    }

}
