package fragnito.U5W3D2.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime timestamp
) {
}
