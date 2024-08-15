package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.auth.LoginRequest;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse register(RegistrationRequest request);

    JwtResponse login(LoginRequest request);
}
