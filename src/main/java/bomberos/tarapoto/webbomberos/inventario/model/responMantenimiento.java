package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.tipoDocIdentidad;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class responMantenimiento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResponMantenimiento;
    @Enumerated(EnumType.STRING)
    private tipoDocIdentidad tipoDocIdentidad;
    private String numeroDocIdentidad;
    private String nombreResponsable;
    private String telefono;
    private String correo;
    private Boolean registroActivo;

}
