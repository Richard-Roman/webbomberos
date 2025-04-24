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
public class componentesEquiposVehiculos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComponentes;
    private String CodigoEquipoVehiculo;
    private String CodigoEjemplarRecurso;
    private Timestamp fechaAsignacionEquipo;
    private Timestamp fechaActualizacion;
    private Boolean registroActivo;

}
