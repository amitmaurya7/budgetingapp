package com.budgetingapp.userservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

	@Column(nullable = false)
	@Email(message = "Email should be in valid format")
	private String email;
	@NotNull(message = "Pasword should not be null")
	private String password;
}
