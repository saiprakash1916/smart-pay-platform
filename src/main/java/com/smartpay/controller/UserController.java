package com.smartpay.controller;

import com.smartpay.dto.LoginRequest;
import com.smartpay.dto.UserRequest;
import com.smartpay.entity.User;
import com.smartpay.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile() {
        return "Secure API - User Profile";
    }

    @PostMapping("/register")
    public User register(@RequestBody UserRequest request) {
        log.info("Register API called for email: {}", request.getEmail());
        User user = userService.registerUser(request);
        log.info("User registered successfully: {}", user.getEmail());
        return user;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());
        return userService.login(request);
    }

    @GetMapping("/admin/dashboard")
    public String adminApi() {
        return "Admin access granted";
    }

    @GetMapping("/home")
    public String roleApi() {
        return "User access granted";
    }
}
