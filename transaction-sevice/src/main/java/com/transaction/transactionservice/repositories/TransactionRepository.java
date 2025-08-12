package com.transaction.transactionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.transactionservice.entities.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

}
