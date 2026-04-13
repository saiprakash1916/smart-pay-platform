package com.smartpay.service;

import com.smartpay.dto.LoginRequest;
import com.smartpay.dto.UserRequest;
import com.smartpay.entity.User;
import com.smartpay.repository.UserRepository;
import com.smartpay.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User registerUser(UserRequest request){
        log.info("Registering user with email: {}", request.getEmail());
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);
        log.info("User saved successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    public String login(LoginRequest request){
        log.info("Login request received for email: {}", request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User not found: {}", request.getEmail());
                    return new RuntimeException("Invalid User");
                });
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            log.error("Invalid password for user: {}", request.getEmail());
            throw new RuntimeException("Invalid Password");
        }
        log.info("Login successful for user: {}", request.getEmail());
        return jwtUtil.generateToken(user.getEmail());
    }
}
