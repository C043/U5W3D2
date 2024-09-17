package fragnito.U5W3D2.payloads;

import jakarta.validation.constraints.NotNull;

public record AuthDTO(
        @NotNull(message = "L'email è obbligatoria")
        String email,
        @NotNull(message = "La password è obbligatoria")
        String password
) {
}
