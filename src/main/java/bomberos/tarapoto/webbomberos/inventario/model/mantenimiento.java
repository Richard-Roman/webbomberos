package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.estadoMantenimiento;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class mantenimiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMantenimiento;
    private Integer idResponMantenimiento;
    private Timestamp fechaEntrega;
    private Timestamp fechaDevolucion;

    @Enumerated(EnumType.STRING)
    private estadoMantenimiento EstadoMantenimiento;

    @Column(columnDefinition = "TINYTEXT")
    private String observaciones;
    private Timestamp fechaCreacion;
    private Boolean registroActivo;

}
