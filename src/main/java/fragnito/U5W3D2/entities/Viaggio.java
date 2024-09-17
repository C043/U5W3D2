package fragnito.U5W3D2.entities;

import fragnito.U5W3D2.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Viaggio {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinazione;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public Viaggio(String destinazione, LocalDate data, StatoViaggio stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}
