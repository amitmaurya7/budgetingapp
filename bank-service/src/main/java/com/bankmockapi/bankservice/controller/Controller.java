package com.bankmockapi.bankservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmockapi.bankservice.dto.MockBankAccountDto;

@RestController
@RequestMapping("/api/bank/")
public class Controller {

	@GetMapping("/maccount")
	public List<MockBankAccountDto> getMockAccounts() {
		return List.of(new MockBankAccountDto(123L, "Mock Savings", "BANKMOCK001"),
				new MockBankAccountDto(456L, "Mock Checking", "BANKMOCK002"));
	}
}
