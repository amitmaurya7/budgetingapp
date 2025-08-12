package com.transaction.transactionservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.transactionservice.dto.TransactionDto;
import com.transaction.transactionservice.entities.Transactions;
import com.transaction.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {

	private final TransactionService transactionService;
	
	TransactionController(TransactionService transactionService){
		this.transactionService = transactionService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionDto transactionDto, @AuthenticationPrincipal Jwt principal){
		String email = principal.getSubject();
		Transactions transaction = transactionService.addTransaction(transactionDto, email);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}
}
