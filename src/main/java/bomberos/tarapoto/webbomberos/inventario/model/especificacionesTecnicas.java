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
public class especificacionesTecnicas {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspeTecnica;
    private String EspeTecnica;
    private String UnidadMedida;
    @Column(columnDefinition = "TINYTEXT")
    private String Descripción;
    private Boolean registroActivo;

}
