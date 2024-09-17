package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotNull(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 10, message = "Username deve contenere minimo 3 caratteri e massimo 10")
        String username,
        @NotNull(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 10, message = "Il nome deve contenere minimo 3 caratteri e massimo 10")
        String nome,
        @NotNull(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 10, message = "Il cognome deve contenere minimo 3 caratteri e massimo 10")
        String cognome,
        @NotNull(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String email,
        @NotNull(message = "La password è obbligatoria")
        @Size(min = 5, message = "La password deve essere di minimo 5 caratteri")
        String password
) {
}
