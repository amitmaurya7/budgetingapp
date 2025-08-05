package com.budgetingapp.userservice.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.budgetingapp.userservice.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto<List<String>>> handleFieldValidationException(MethodArgumentNotValidException ex){
		List<String> errors = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(fieldError -> fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseDto<List<String>> response = ResponseDto.<List<String>>builder()
				.message("Valid field")
				.data(errors)
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.localDateTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
