package valerio.U6W3D1.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(
        String message,
        LocalDateTime timeStamp
) {
}
