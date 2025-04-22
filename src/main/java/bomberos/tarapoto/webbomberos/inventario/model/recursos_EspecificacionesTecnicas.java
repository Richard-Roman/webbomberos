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
public class recursos_EspecificacionesTecnicas {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspeTecnicaRecursos;
    private Integer idEspeTecnica;
    private Integer idRecurso;
    private Boolean registroActivo;

}
