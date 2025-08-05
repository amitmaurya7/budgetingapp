package com.authservcie.authservice.serviceimpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservcie.authservice.communication.UserClient;
import com.authservcie.authservice.dto.RegisterRequestDto;
import com.authservcie.authservice.service.AuthService;
import com.authservcie.authservice.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserClient userClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userClient = userClient;
    }
	
	@Override
	public String register(RegisterRequestDto register) {
		 if (userClient.checkIfEmailExists(register.getEmail())) {
	            throw new RuntimeException("Email already registered");
	        }

	        String hashedPassword = passwordEncoder.encode(register.getPassword());
	        userClient.createUser(register.getEmail(), hashedPassword);
	        return jwtUtil.generateToken(register.getEmail());
	}

}
