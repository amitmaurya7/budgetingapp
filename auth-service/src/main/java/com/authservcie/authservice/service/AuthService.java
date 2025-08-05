package com.authservcie.authservice.service;

import com.authservcie.authservice.dto.RegisterRequestDto;

public interface AuthService {

	public String register(RegisterRequestDto register);
}
