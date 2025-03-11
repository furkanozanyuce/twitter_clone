package com.foy.twitter.controller;

import com.foy.twitter.dto.LoginRequest;
import com.foy.twitter.dto.LoginResponse;
import com.foy.twitter.dto.RegisterRequest;
import com.foy.twitter.dto.RegisterResponse;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {

        User user = authService.register(request.getEmail(), request.getUserName(), request.getPassword());

        return new RegisterResponse(user.getEmail(), "User successfully registered!");
    }

    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse(request.getEmail(), "Login successful!");
    }
}
