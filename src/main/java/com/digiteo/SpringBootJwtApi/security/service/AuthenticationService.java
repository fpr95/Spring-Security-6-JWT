package com.digiteo.SpringBootJwtApi.security.service;

import com.digiteo.SpringBootJwtApi.model.Role;
import com.digiteo.SpringBootJwtApi.model.UserEntity;
import com.digiteo.SpringBootJwtApi.repository.UserEntityRepository;
import com.digiteo.SpringBootJwtApi.security.AuthRequest;
import com.digiteo.SpringBootJwtApi.security.AuthResponse;
import com.digiteo.SpringBootJwtApi.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest req) {
        UserEntity user = UserEntity.builder()
                .name(req.getName())
                .pwd(encoder.encode(req.getPwd()))
                .email(req.getEmail())
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = service.generateJwtToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPwd()));
        // here the exception should be handled properly(send the msg back to the client or something)
        // but it's for the demo purposes
        UserEntity user = repository.findByEmail(req.getEmail())
                .orElseThrow();
        String jwtToken = service.generateJwtToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
