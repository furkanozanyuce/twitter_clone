package com.foy.twitter.controller;

import com.foy.twitter.dto.RegisterRequest;
import com.foy.twitter.dto.RegisterResponse;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {

        User user = authService.register(request.getEmail(), request.getPassword());

        return new RegisterResponse(user.getEmail(), "User successfully registered!");
    }
}
