package kz.edu.astanait.dashboard.controller;


import jakarta.validation.Valid;
import kz.edu.astanait.dashboard.controller.api.ApiDataResponse;
import kz.edu.astanait.dashboard.dto.auth.JwtResponse;
import kz.edu.astanait.dashboard.dto.auth.LoginRequest;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiDataResponse<JwtResponse>> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(authService.login(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<JwtResponse>> register(@Valid @RequestBody RegistrationRequest request) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(authService.register(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }
}
