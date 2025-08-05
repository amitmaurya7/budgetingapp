package com.authservcie.authservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserClientDto {

	private String message;
	private boolean data;
	private int statusCode;
	private LocalDateTime localDateTime;
}
