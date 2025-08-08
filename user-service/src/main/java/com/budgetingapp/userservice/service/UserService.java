package com.budgetingapp.userservice.service;

import org.springframework.security.oauth2.jwt.Jwt;

import com.budgetingapp.userservice.dto.LinkRequestDto;
import com.budgetingapp.userservice.dto.ResponseDto;
import com.budgetingapp.userservice.dto.UserRegistrationDto;
import com.budgetingapp.userservice.entities.User;

public interface UserService {

	public ResponseDto<Boolean> checkEmailExist(String email);
	public ResponseDto<User> registerUser(UserRegistrationDto register);
	public User getUserByEmail(String email);
	public String linkBankAccount(LinkRequestDto linkRequestdto, Jwt principal);
	
}
