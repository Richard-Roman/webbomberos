package bomberos.tarapoto.webbomberos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Permisos")

public class Permisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_Permisos;

    @Column(unique = true, nullable = false, updatable = false)
    private String nombre;

}
