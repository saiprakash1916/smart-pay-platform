package com.smartpay.service;

import com.smartpay.entity.RefreshToken;
import com.smartpay.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    public final RefreshTokenRepository refreshTokenRepository;

    public String createRefreshToken(String email) {
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .email(email)
                .expiryDate(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .build();

        refreshTokenRepository.save(refreshToken);

        return token;
    }

    public RefreshToken findByToken(String token){
        return refreshTokenRepository.findByToken(token).orElse(null);
    }
}
