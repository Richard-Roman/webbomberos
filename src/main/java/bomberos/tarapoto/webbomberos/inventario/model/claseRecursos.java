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
public class claseRecursos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClaseRecurso;
    private String nombreClase;
    @Column(columnDefinition = "TINYTEXT")
    private String Descripción;
    private Boolean registroActivo;

}
