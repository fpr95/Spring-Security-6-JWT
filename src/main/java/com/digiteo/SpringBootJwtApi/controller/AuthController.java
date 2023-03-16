package com.digiteo.SpringBootJwtApi.controller;

import com.digiteo.SpringBootJwtApi.security.AuthRequest;
import com.digiteo.SpringBootJwtApi.security.AuthResponse;
import com.digiteo.SpringBootJwtApi.security.RegisterRequest;
import com.digiteo.SpringBootJwtApi.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register-user")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req){
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/authenticate-user")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest req){
        return ResponseEntity.ok(service.authenticate(req));
    }
}
