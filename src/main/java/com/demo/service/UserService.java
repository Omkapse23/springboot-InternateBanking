package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dto.LoginRequest;
import com.demo.dto.RegisterRequest;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REGISTER USER
    public String registerUser(RegisterRequest request) {

        // CHECK EMAIL ALREADY EXISTS
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // CREATE NEW USER
        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // ENCRYPT PASSWORD
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // DEFAULT ROLE
        user.setRole("USER");

        // SAVE USER
        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN USER
    public String loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        boolean isPasswordCorrect =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordCorrect) {
            return "Invalid Password";
        }

        return "Login Successful";
    }
}