package com.transaction.transactionservice.service;

import java.util.List;

import com.transaction.transactionservice.dto.TransactionDto;
import com.transaction.transactionservice.entities.Transactions;

public interface TransactionService {

	public Transactions addTransaction(TransactionDto transactionDto, String email);
	public String updateCategory(Long id, String newCategory);
	public  List<Transactions> getTransactionsByUserAndMonth(String userEmail,int month,int year);
	public double calculateTotalIncome(String userEmail,int month,int year);
	public double calculateTotalExpenses(String userEmail,int month,int year);
}
