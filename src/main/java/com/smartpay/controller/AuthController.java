package com.smartpay.controller;

import com.smartpay.entity.RefreshToken;
import com.smartpay.service.RefreshTokenService;
import com.smartpay.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    @PostMapping("/refresh")
    public String refresh(@RequestParam String refreshToken){
        RefreshToken token = refreshTokenService.findByToken(refreshToken);

        if(token == null || token.getExpiryDate().before(new Date())){
            throw new RuntimeException("Invalid or expired refresh token");
        }

        return jwtUtil.generateToken(token.getEmail(), "USER");
    }
}
