package fragnito.U5W3D2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;
    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;
    private String note;

    public Prenotazione(Dipendente dipendente, Viaggio viaggio, String note) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.dataRichiesta = LocalDate.now();
        this.note = note;
    }
}
