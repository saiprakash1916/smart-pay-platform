package com.smartpay.service;

import com.smartpay.entity.BlacklistedToken;
import com.smartpay.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlacklistService {

    private final BlacklistedTokenRepository repository;

    public void blankedList(String token){
        BlacklistedToken blacklistedToken = BlacklistedToken.builder().token(token).build();
         repository.save(blacklistedToken);
    }

    public Boolean isBlacklisted(String token){
        return repository.existsByToken(token);
    }
}
