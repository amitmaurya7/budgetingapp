package com.authservcie.authservice.communication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.authservcie.authservice.dto.RegisterDto;
import com.authservcie.authservice.dto.ResponseUserClientDto;
import com.authservcie.authservice.dto.UserResponse;

@Component
public class UserClient {

	private final RestTemplate restTemplate;

	public UserClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	RegisterDto registerDto = new RegisterDto();

	public boolean checkIfEmailExists(String email) {
		ResponseEntity<ResponseUserClientDto> response = restTemplate
				.getForEntity("http://localhost:8081//api/v1/user/email/" + email, ResponseUserClientDto.class);
		boolean flag = response.getBody().isData();
		return flag;
	}

	public void createUser(String email, String hashedPassword) {
		registerDto.setEmail(email);
		registerDto.setPassword(hashedPassword);
		restTemplate.postForEntity("http://localhost:8081//api/v1/user/register", registerDto, Void.class);
	}
	
	public UserResponse getUserById(String email) {
		ResponseEntity<UserResponse> response = restTemplate
				.getForEntity("http://localhost:8081//api/v1/user/" + email, UserResponse.class);
		return response.getBody();
	}
}
