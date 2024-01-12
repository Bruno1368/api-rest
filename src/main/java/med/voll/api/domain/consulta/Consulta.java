package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Consulta")
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime data;

}