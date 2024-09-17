package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotNull(message = "La destinazione è obbligatoria")
        String destinazione,
        @NotNull(message = "La data è obbligatoria")
        LocalDate data,
        @NotNull(message = "Lo stato è obbligatorio")
        @Pattern(regexp = "^(IN_PROGRAMMA|COMPLETATO)$", message = "Lo stato può essere solo IN_PROGRAMMA o COMPLETATO")
        String stato
) {
}
