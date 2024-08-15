package kz.edu.astanait.dashboard.service.impl;


import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.dto.user.UserDto;
import kz.edu.astanait.dashboard.enums.UserRole;
import kz.edu.astanait.dashboard.exceptions.UserAlreadyExistsException;
import kz.edu.astanait.dashboard.mapper.UserMapper;
import kz.edu.astanait.dashboard.model.UserEntity;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("User %s already exists".formatted(request.username()));
        }

        var userEntity = UserMapper.MAPPER.mapToEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userEntity.setRole(UserRole.USER);

        return userRepository.save(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        var user = userRepository.findById(dto.id()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserMapper.MAPPER.updateEntity(dto, user);

        var savedUser = userRepository.save(user);
        return UserMapper.MAPPER.mapToDto(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()))
        );
    }
}
