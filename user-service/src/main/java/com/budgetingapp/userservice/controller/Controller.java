package com.budgetingapp.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetingapp.userservice.dto.ResponseDto;
import com.budgetingapp.userservice.dto.UserRegistrationDto;
import com.budgetingapp.userservice.entities.User;
import com.budgetingapp.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/")
public class Controller {

	@Autowired
	private UserService userService;
	
	@GetMapping ("/email/{email}")
	public ResponseEntity<ResponseDto<Boolean>> checkUserExist(@Valid @PathVariable String email){
		ResponseDto<Boolean> response = userService.checkEmailExist(email);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto<User>> registerUser(@Valid @RequestBody UserRegistrationDto register){
		ResponseDto<User> response = userService.registerUser(register);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email){
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}
}
