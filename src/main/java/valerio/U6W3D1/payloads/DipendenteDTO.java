package valerio.U6W3D1.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendenteDTO(
        @NotEmpty(message = "il nome è obbligatorio")
        String name,
        @NotEmpty(message = "il cognome è obbligatorio")
        String surname,

        @NotEmpty(message = "l'username è obbligatorio")
        String username,

        @NotEmpty(message = "l'email è obbligatoria")
        @Email(message = "l'email non è valida")
        String email,

        @NotEmpty(message = "la password è obbligatoria")
        String password,

        String profileImg

) {
}
