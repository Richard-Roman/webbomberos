package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.estadoSeguro;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class seguroVehicular {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeguroVehicular;
    private String docSeguroVehicular;
    private String CodigoEjemplarRecurso;
    private String numeroPoliza;
    private String aseguradora;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private BigDecimal costo;
    @Enumerated(EnumType.STRING)
    private estadoSeguro estado;
    private Boolean registroActivo;
    private Timestamp fechaRegistro;

}
