package kz.edu.astanait.dashboard.dto.user;

public record UserDto(
        Long id,
        String username,
        String firstname,
        String lastname
) {
}
