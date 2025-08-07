package com.authservcie.authservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservcie.authservice.communication.UserClient;
import com.authservcie.authservice.dto.LoginDto;
import com.authservcie.authservice.dto.RegisterRequestDto;
import com.authservcie.authservice.dto.UserResponse;
import com.authservcie.authservice.service.AuthService;
import com.authservcie.authservice.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserClient userClient;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, UserClient userClient, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userClient = userClient;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequestDto request) {
        String token = authService.register(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto){
    	UserResponse user = userClient.getUserById(loginDto.getEmail());
    	if(user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    	}
    	String token = jwtUtil.generateToken(loginDto.getEmail());
    	return ResponseEntity.ok(Map.of("tokena :", token));
    }
}

