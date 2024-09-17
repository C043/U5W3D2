package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StatoViaggioDTO(
        @Pattern(regexp = "^(IN_PROGRAMMA|COMPLETATO)$", message = "Lo stato può essere solo IN_PROGRAMMA o COMPLETATO")
        @NotNull(message = "Lo stato è obbligatorio")
        String stato
) {
}
