package kz.edu.astanait.dashboard.controller;

import kz.edu.astanait.dashboard.dto.auth.JwtResponse;
import kz.edu.astanait.dashboard.dto.auth.LoginRequest;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.dto.user.UserDto;
import kz.edu.astanait.dashboard.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    void login_validRequest_shouldReturnJwtResponse() {
        // ARRANGE
        var request = new LoginRequest("test_user", "123456789");
        var jwtResponse = new JwtResponse(
                new UserDto(1L, "test_user", null, null),
                "accessToken",
                "refreshToken"
        );

        when(authService.login(request)).thenReturn(jwtResponse);

        // ACT
        var result = authController.login(request);

        // ASSERT
        assertEquals(200, result.getStatusCode().value());
        assertEquals("OK", Objects.requireNonNull(result.getBody()).getMsg());
        assertEquals("accessToken", result.getBody().getData().getAccessToken());
        assertEquals("refreshToken", result.getBody().getData().getRefreshToken());
    }

    @Test
    void login_invalidCredentialsRequest_shouldReturnErrorMessage() {
        // ARRANGE
        var loginRequest = new LoginRequest("username", "password");
        var errorMessage = "Invalid credentials";

        when(authService.login(any(LoginRequest.class))).thenThrow(new RuntimeException(errorMessage));

        // ACT
        var result = authController.login(loginRequest);

        // ASSERT
        assertTrue(result.getStatusCode().isError());
        assertEquals(errorMessage, Objects.requireNonNull(result.getBody()).getMsg());
        assertFalse(result.getBody().isSuccess());
    }

    @Test
    void register_validRequest_shouldReturnJwtResponse() {
        // ARRANGE
        var request = new RegistrationRequest("username", "password", "name", "lastname");
        var jwtResponse = new JwtResponse(
                new UserDto(1L, "test_user", null, null),
                "accessToken",
                "refreshToken"
        );

        when(authService.register(request)).thenReturn(jwtResponse);

        // ACT
        var result = authController.register(request);

        // ASSERT
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertEquals("OK", Objects.requireNonNull(result.getBody()).getMsg());
        assertEquals("accessToken", result.getBody().getData().getAccessToken());
        assertEquals("refreshToken", result.getBody().getData().getRefreshToken());
    }

    @Test
    void register_invalidRequest_shouldReturnErrorMsg() {
        // ARRANGE
        var request = new RegistrationRequest("username", "password", "name", "lastname");
        var errorMessage = "Registration failed";

        when(authService.register(any(RegistrationRequest.class))).thenThrow(new RuntimeException(errorMessage));

        // ACT
        var result = authController.register(request);

        // ASSERT
        assertTrue(result.getStatusCode().isError());
        assertEquals(errorMessage, Objects.requireNonNull(result.getBody()).getMsg());
        assertFalse(result.getBody().isSuccess());
    }
}
