package kz.edu.astanait.dashboard.service;


import kz.edu.astanait.dashboard.dto.auth.LoginRequest;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.model.UserEntity;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.impl.AuthServiceImpl;
import kz.edu.astanait.dashboard.utils.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_successfulRegistration_shouldCreateUserAndReturnTokens() {
        // ARRANGE
        var request = new RegistrationRequest("username", "password", "name", "lastname");
        var user = getUserEntity(request);
        var token = "token";

        when(userService.createUser(request)).thenReturn(user);
        when(jwtTokenProvider.generateToken(user)).thenReturn(token);

        // ACT
        var result = authService.register(request);

        // ASSERT
        assertEquals("username", result.getUser().username());
        assertEquals(token, result.getAccessToken());
    }

    @Test
    void login_successfulLogin_shouldAuthenticateAndReturnJwtResponse() {
        // ARRANGE
        var request = new LoginRequest("username", "password");
        var user = new UserEntity();
        user.setUsername(request.username());
        var token = "token";

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password())))
                .thenReturn(null);
        when(userRepository.findByUsername(request.username())).thenReturn(Optional.of(user));
        when(jwtTokenProvider.generateToken(user)).thenReturn(token);

        // ACT
        var result = authService.login(request);

        // ASSERT
        assertEquals("username", result.getUser().username());
        assertEquals(token, result.getAccessToken());
    }

    private UserEntity getUserEntity(RegistrationRequest request) {
        var user = new UserEntity();
        user.setUsername(request.username());
        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        return user;
    }
}
