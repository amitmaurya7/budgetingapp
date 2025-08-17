package com.transaction.transactionservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.transactionservice.dto.TransactionDto;
import com.transaction.transactionservice.entities.Transactions;
import com.transaction.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {

	private final TransactionService transactionService;

	TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/create")
	public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionDto transactionDto,
			@AuthenticationPrincipal Jwt principal) {
		String email = principal.getSubject();
		Transactions transaction = transactionService.addTransaction(transactionDto, email);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}

	@PutMapping("/{id}/category")
	public ResponseEntity<Map<String, String>> updateCategory(@PathVariable Long id,
			@RequestBody Map<String, String> requestBody) {

		String newCategory = requestBody.get("category");
		String updatedCategory = transactionService.updateCategory(id, newCategory);

		return ResponseEntity.ok(Collections.singletonMap("category", updatedCategory));
	}

	@GetMapping("/expenses")
	public ResponseEntity<List<Transactions>> getTransactions(@RequestParam String userEmail, @RequestParam int month,
			@RequestParam int year) {

		List<Transactions> transactions = transactionService.getTransactionsByUserAndMonth(userEmail, month, year);
		return ResponseEntity.ok(transactions);
	}
	
	@GetMapping("/total-income")
    public ResponseEntity<Double> getTotalIncome(
            @RequestParam String userEmail,
            @RequestParam int month,
            @RequestParam int year) {

        Double totalIncome = transactionService.calculateTotalIncome(userEmail, month, year);
        return ResponseEntity.ok(totalIncome);
    }

    // Endpoint to get total expenses
    @GetMapping("/total-expenses")
    public ResponseEntity<Double> getTotalExpenses(
            @RequestParam String userEmail,
            @RequestParam int month,
            @RequestParam int year) {

        Double totalExpenses = transactionService.calculateTotalExpenses(userEmail, month, year);
        return ResponseEntity.ok(totalExpenses);
    }
}
