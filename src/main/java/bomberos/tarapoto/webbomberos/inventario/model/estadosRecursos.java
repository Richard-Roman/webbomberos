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
public class estadosRecursos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;
    private String NombreEstado;
    private Boolean registroActivo;

}
