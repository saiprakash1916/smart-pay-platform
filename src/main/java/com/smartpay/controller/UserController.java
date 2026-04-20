package com.smartpay.controller;

import com.smartpay.dto.LoginRequest;
import com.smartpay.dto.UserRequest;
import com.smartpay.entity.User;
import com.smartpay.service.BlacklistService;
import com.smartpay.service.UserService;
import com.smartpay.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final BlacklistService blacklistService;

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

    @Operation(summary = "User Login", description = "Authenticate user and return JWT + Refresh Token")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());
        return userService.login(request);
    }

    @Operation(summary = "Logout", description = "Invalidate JWT token")
    @PostMapping("/logout")
    public String logout(@RequestHeader ("Authorization") String header) {
        log.info("Logout request received");

        if(header == null || !header.startsWith("Bearer ")){
            log.warn("Invalid Authorization header during logout");
            throw new RuntimeException("Invalid token format");
        }
        String token = header.substring(7);
        try {
            String email = jwtUtil.extractEmail(token);
            log.info("Logout request for user: {}", email);
        }catch (Exception e){
            log.warn("Unable to extract user from token");
        }
        blacklistService.blankedList(token);
        log.info("Token successfully blacklisted");
        return "Logged out successfully";
    }

    @GetMapping("/home")
    public String roleApi() {
        return "User access granted";
    }
}
