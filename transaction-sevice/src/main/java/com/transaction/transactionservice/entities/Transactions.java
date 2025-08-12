package com.transaction.transactionservice.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transaction")
@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false)
	private double amount;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private LocalDateTime localDateTime = LocalDateTime.now();
	
	@Column(nullable = false)
	private String note;
	
	@Column(nullable = false)
	private String userEmail;
}
