package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.dto.user.UserDto;
import kz.edu.astanait.dashboard.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(RegistrationRequest request);

    UserDto updateUser(UserDto dto);
}
