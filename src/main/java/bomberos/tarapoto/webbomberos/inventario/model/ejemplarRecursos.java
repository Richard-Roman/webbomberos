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
public class ejemplarRecursos {

    @Id
    private String CodigoEjemplarRecurso;
    @Column(columnDefinition = "TINYTEXT")
    private String UbicacionEjemplarRecursos;
    private Integer idRecurso;
    private Integer idEstado;
    private Boolean registroActivo;

}
