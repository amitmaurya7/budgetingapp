package com.transaction.transactionservice.serviceimpl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.transaction.transactionservice.dto.TransactionDto;
import com.transaction.transactionservice.dto.TransactionKafka;
import com.transaction.transactionservice.entities.Transactions;
import com.transaction.transactionservice.repositories.TransactionRepository;
import com.transaction.transactionservice.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final KafkaTemplate<String, TransactionKafka>kafkaTemplate;
	
	TransactionServiceImpl(TransactionRepository transactinRepository, KafkaTemplate<String, TransactionKafka> kafkaTempalte){
		this.transactionRepository = transactinRepository;
		this.kafkaTemplate = kafkaTempalte;
	}
 
	@Override
	public Transactions addTransaction(TransactionDto transactionDto, String email) {
		Transactions transaction = new Transactions();
		transaction.setAmount(transactionDto.getAmount());
		transaction.setCategory(transactionDto.getCategory());
		transaction.setNote(transactionDto.getNote());
		transaction.setUserEmail(transactionDto.getUserEmail());
		Transactions saved = transactionRepository.save(transaction);
		TransactionKafka kafkaMessage = new TransactionKafka(saved.getId(), saved.getAmount(),saved.getCategory(), saved.getNote(), saved.getUserEmail());
		kafkaTemplate.send("transactions", kafkaMessage);
		return saved;
	}

}
