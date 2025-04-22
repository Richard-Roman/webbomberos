package bomberos.tarapoto.webbomberos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import bomberos.tarapoto.webbomberos.inventario.model.enums.categoria;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class recursos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecurso;
    private String nombreRecurso;
    @Enumerated(EnumType.STRING)
    private categoria categoria;
    private Integer idClaseRecurso;
    @Column(columnDefinition = "TINYTEXT")
    private String Descripción;
    private Boolean registroActivo;

}
