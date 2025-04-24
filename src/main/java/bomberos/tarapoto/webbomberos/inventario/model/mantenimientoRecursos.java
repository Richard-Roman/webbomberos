package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.tipoMantenimiento;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class mantenimientoRecursos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMantenimientoRecursos;
    private Integer idMantenimiento;
    private String CodigoEjemplarRecurso;
    @Enumerated(EnumType.STRING)
    private tipoMantenimiento TipoMantenimiento;
    private BigDecimal costoMantenimiento;
    private Timestamp fechaRegistro;
    private Boolean registroActivo;

}
