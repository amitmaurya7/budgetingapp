package com.budgetingapp.userservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.budgetingapp.userservice.dto.ResponseDto;
import com.budgetingapp.userservice.dto.UserRegistrationDto;
import com.budgetingapp.userservice.entities.User;
import com.budgetingapp.userservice.exception.UserNotExistException;
import com.budgetingapp.userservice.repositories.UserRepository;
import com.budgetingapp.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public ResponseDto<Boolean> checkEmailExist(String email) {
		boolean exist = userRepository.findByEmail(email).isPresent();
		return ResponseDto.response("Is user present ?", exist, 200);
	}

	@Override
	public ResponseDto<User> registerUser(UserRegistrationDto register) {
		User user = new User();
		if(userRepository.findByEmail(register.getEmail()).isPresent()) {
			return ResponseDto.response("User Already exist", null, HttpStatus.CONFLICT.value());
		}
		user.setEmail(register.getEmail());
		user.setPassword(register.getPassword());
		userRepository.save(user);
		return ResponseDto.response("User Register successfully", user, HttpStatus.CREATED.value());
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(()->  new UserNotExistException("User with email:"+ email+" is not exist"));
		return user;
	}

}
