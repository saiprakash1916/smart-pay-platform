package com.smartpay.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final SecretKey SECRET = Keys.hmacShaKeyFor("smartpay-secret-key-smartpay-secret-key".getBytes());

    public String generateToken(String email){
        log.info("Generating JWT token for user: {}", email);
        String token =  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
        log.info("JWT token generated successfully");
        return token;
    }
}