package kz.edu.astanait.dashboard.dto.auth;

public record RegistrationRequest(
        String username,
        String password,
        String firstname,
        String lastname
) {
}
