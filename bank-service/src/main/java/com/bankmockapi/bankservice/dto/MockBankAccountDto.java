package com.bankmockapi.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockBankAccountDto {

	private Long id;
	private String accountName;
	private String bankCode;
}
