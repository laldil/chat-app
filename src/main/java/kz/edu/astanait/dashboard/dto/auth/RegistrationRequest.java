package kz.edu.astanait.dashboard.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequest(
        @NotNull(message = "username cannot be empty")
        @NotBlank
        String username,

        @NotNull(message = "password cannot be empty")
        String password,

        String firstname,

        String lastname
) {
}
