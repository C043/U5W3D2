package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MinePrenotazioneDTO(
        @NotNull(message = "L'id del viaggio è obbligatorio")
        @Min(value = 1, message = "L'id del viaggio è obbligatorio")
        int viaggioId,
        String note
) {
}
