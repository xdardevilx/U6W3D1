package valerio.U6W3D1.payloads;

import jakarta.validation.constraints.NotEmpty;

public record DispositivoDTO(


        @NotEmpty(message = "deve essere specificato")
        String tipologia,

        @NotEmpty(message = "deve essere specificato")
        String stato
) {
}
