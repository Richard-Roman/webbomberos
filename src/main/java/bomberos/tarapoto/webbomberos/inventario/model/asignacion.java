package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class asignacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignacion;
    private Integer idpersonal;
    private String CodigoEjemplarRecurso;
    private Timestamp fechaAsignacion;
    private Date fechaDevolucion;
    private Timestamp fechaRegistro;
    private Boolean registroActivo;

}

