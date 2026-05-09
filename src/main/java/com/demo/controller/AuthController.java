package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.LoginRequest;
import com.demo.dto.RegisterRequest;
import com.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register API
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }

    // Login API
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {

        return userService.loginUser(request);
    }
}