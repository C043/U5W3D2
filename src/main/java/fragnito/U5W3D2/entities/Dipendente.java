package fragnito.U5W3D2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendente {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String avatar;

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }
}
