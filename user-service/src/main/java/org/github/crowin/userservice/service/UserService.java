package org.github.crowin.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.crowin.userservice.dto.LoginRequest;
import org.github.crowin.userservice.dto.NewUserRequest;
import org.github.crowin.userservice.exception.ClientBasicException;
import org.github.crowin.userservice.mapper.UserMapper;
import org.github.crowin.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.github.crowin.userservice.util.ClientErrorCode.INVALID_CREDENTIALS;
import static org.github.crowin.userservice.util.ClientErrorCode.USERNAME_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void createUser(NewUserRequest newUser){
        if (userRepository.findByUsername(newUser.username()).isPresent()) {
            log.error("Username already exists: {}", newUser.username());
            throw new ClientBasicException("Username already exists", USERNAME_ALREADY_EXISTS);
        }
        var user = userMapper.toEntity(newUser);
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest){
        var foundUser = userRepository
                .findByUsername(loginRequest.username())
                .orElseThrow(() -> new ClientBasicException("User not found: " + loginRequest.username(), USERNAME_ALREADY_EXISTS));
        if (!passwordEncoder.matches(loginRequest.password(), foundUser.getPasswordHash())) {
            log.error("Invalid password for user: {}", loginRequest.username());
            throw new ClientBasicException("Invalid credentials", INVALID_CREDENTIALS);
        }
    }
}
