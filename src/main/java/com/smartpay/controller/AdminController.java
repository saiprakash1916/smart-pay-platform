package com.smartpay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        log.info("Admin dashboard accessed");
        return "Welcome to Admin Dashboard";
    }

    @GetMapping("/users")
    public String adminApi() {
        log.info("Admin managing users");
        return "Admin can view all users";
    }
}
