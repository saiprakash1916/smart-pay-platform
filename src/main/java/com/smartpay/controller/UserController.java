package com.smartpay.controller;

import com.smartpay.dto.UserRequest;
import com.smartpay.entity.User;
import com.smartpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }
}
