package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.auth.JwtResponse;
import kz.edu.astanait.dashboard.dto.auth.LoginRequest;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.mapper.UserMapper;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.AuthService;
import kz.edu.astanait.dashboard.service.UserService;
import kz.edu.astanait.dashboard.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    // todo refresh token

    @Override
    public JwtResponse register(RegistrationRequest request) {
        var user = userService.createUser(request);
        var token = jwtTokenProvider.generateToken(user);
        return new JwtResponse(UserMapper.MAPPER.mapToDto(user), token, token);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new EntityNotFoundException("User %s not found".formatted(request.username())));
        var token = jwtTokenProvider.generateToken(user);

        return new JwtResponse(UserMapper.MAPPER.mapToDto(user), token, token);
    }
}
