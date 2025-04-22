package bomberos.tarapoto.webbomberos.personal.model;

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
@Table(name = "telefonos_emergencia")
@SQLDelete(sql = "UPDATE personal SET desactivado = '1' WHERE id_personal = ?")
@Where(clause = "desactivado = '0'")

public class TelefonoEmergencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelefono;

    @ManyToOne
    @JoinColumn(name = "id_personal")
    private Personal personal;

    private String nombreContacto;
    private String parentesco;
    private String telefono;

    @Column(nullable = false)
    private String desactivado = "0";

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        return "TelefonoEmergencia [idTelefono=" + idTelefono + ", personal=" + personal + ", nombreContacto="
                + nombreContacto + ", parentesco=" + parentesco + ", telefono=" + telefono + ", desactivado="
                + desactivado + "]";
    }

}
