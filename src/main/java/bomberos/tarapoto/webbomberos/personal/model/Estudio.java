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
@Table(name = "estudios")
@SQLDelete(sql = "UPDATE estudios SET registroActivo = '0' WHERE id_estudio = ?")
@Where(clause = "registroActivo = '1'")
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudio;

    @ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;

    private String nombre;
    private String descripcion;
    private LocalDate fechaEmision;
    private LocalDate fechaExpedicion;
    private String tipo;
    private String imagenDocumento;
    private String registroActivo = "1";

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagenDocumento() {
        return imagenDocumento;
    }

    public void setImagenDocumento(String imagenDocumento) {
        this.imagenDocumento = imagenDocumento;
    }

    public String isDesactivado() {
        return registroActivo;
    }

    public void setDesactivado(String registroActivo) {
        this.registroActivo = registroActivo;
    }

    @Override
    public String toString() {
        return "Estudio [idEstudio=" + idEstudio + ", personal=" + personal + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", fechaEmision=" + fechaEmision + ", fechaExpedicion=" + fechaExpedicion + ", tipo="
                + tipo + ", imagenDocumento=" + imagenDocumento + ", desactivado=" + registroActivo + "]";
    }

}
