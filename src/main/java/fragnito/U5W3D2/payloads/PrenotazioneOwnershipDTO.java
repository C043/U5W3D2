package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.Min;

public record PrenotazioneOwnershipDTO(
        @Min(value = 1, message = "L'id del dipendente è obbligatorio")
        int dipendenteId
) {
}
