package com.budgetingapp.userservice.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include .NON_NULL)
@Builder
public class ResponseDto<T> {

	private String message;
	private T data;
	private int statusCode;
	private LocalDateTime localDateTime;
	
	public static <T> ResponseDto<T> response(String message, T data, int statusCode){
		return ResponseDto.<T>builder()
				.message(message)
				.data(data)
				.statusCode(statusCode)
				.localDateTime(LocalDateTime.now())
				.build();
	}
}
