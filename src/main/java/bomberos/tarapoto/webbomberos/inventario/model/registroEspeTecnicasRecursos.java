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
public class registroEspeTecnicasRecursos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistroEspeTecnicasRecursos;
    private Integer idEspeTecnica;
    private String CodigoEjemplarRecurso;
    private String ValorEspeTecnica;
    private Boolean registroActivo;

}
