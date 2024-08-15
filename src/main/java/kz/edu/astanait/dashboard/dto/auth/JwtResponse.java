package kz.edu.astanait.dashboard.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.edu.astanait.dashboard.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse {
    private UserDto user;

    private String accessToken;

    private String refreshToken;

    @JsonProperty("type")
    private final String TYPE = "Bearer ";
}
