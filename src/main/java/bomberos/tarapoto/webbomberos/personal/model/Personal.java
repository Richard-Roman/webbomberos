package bomberos.tarapoto.webbomberos.personal.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal")
@SQLDelete(sql = "UPDATE personal SET desactivado = '1' WHERE idPersonal = ?")
@Where(clause = "desactivado = '0'")

public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonal;

    @Column(unique = true, length = 15)
    private String dni;

    @Column(length = 20)
    private String codigoBombero;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "desactivado = '0'") // Solo teléfonos activosa
    private List<TelefonoEmergencia> telefonosEmergencia = new ArrayList<>();

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "desactivado = '0'")
    private List<LicenciaSuspendido> licencias = new ArrayList<>();

    @OneToMany(mappedBy = "personal", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = false)
    @Where(clause = "desactivado = '0'")
    private List<Estudio> estudios = new ArrayList<>();

    private String nombres;
    private String apellidos;
    private String tipoSangre;

    private LocalDate fechaNacimiento;
    private String grado;
    private String cargo;
    private String alergias;
    private String fotoPerfil;
    private String estado;
    private String desactivado;

    public Integer getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoBombero() {
        return codigoBombero;
    }

    public void setCodigoBombero(String codigoBombero) {
        this.codigoBombero = codigoBombero;
    }

    public List<TelefonoEmergencia> getTelefonosEmergencia() {
        return telefonosEmergencia;
    }

    public void setTelefonosEmergencia(List<TelefonoEmergencia> telefonosEmergencia) {
        this.telefonosEmergencia = telefonosEmergencia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        return "Personal [idPersonal=" + idPersonal + ", dni=" + dni + ", codigoBombero=" + codigoBombero + ", nombres="
                + nombres + ", apellidos=" + apellidos + ", tipoSangre=" + tipoSangre + ", fechaNacimiento="
                + fechaNacimiento + ", grado=" + grado + ", cargo=" + cargo + ", alergias=" + alergias + ", fotoPerfil="
                + fotoPerfil + ", estado=" + estado + ", desactivado=" + desactivado + "]";
    }

    public List<LicenciaSuspendido> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<LicenciaSuspendido> licencias) {
        this.licencias = licencias;
    }

    public List<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<Estudio> estudios) {
        this.estudios = estudios;
    }

}
