package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.resultadoRevision;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class revisionesTecnica {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevisionTecnica;
    private String CodigoEjemplarRecurso;
    private Date fechaRevisionTecnica;
    @Enumerated(EnumType.STRING)
    private resultadoRevision resultado;
    private String CentroRevision;
    private String UbicacionCentroRevision;
    private String numCertInspeccion;
    @Column(columnDefinition = "TINYTEXT")
    private String Observaciones;
    private String docCertificado;
    private Boolean registroActivo;
    private Timestamp fechaRegistro;

}
