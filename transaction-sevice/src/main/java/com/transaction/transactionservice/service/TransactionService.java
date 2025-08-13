package com.transaction.transactionservice.service;

import com.transaction.transactionservice.dto.TransactionDto;
import com.transaction.transactionservice.entities.Transactions;

public interface TransactionService {

	public Transactions addTransaction(TransactionDto transactionDto, String email);
	public String updateCategory(Long id, String newCategory);
}
