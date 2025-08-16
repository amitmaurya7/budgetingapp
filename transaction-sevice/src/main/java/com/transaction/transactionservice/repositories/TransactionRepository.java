package com.transaction.transactionservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transaction.transactionservice.entities.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

	@Query("SELECT t FROM Transactions t " + "WHERE t.userEmail = :userEmail "
			+ "AND FUNCTION('MONTH', t.localDateTime) = :month " + "AND FUNCTION('YEAR', t.localDateTime) = :year")
	List<Transactions> findByUserEmailAndMonthAndYear(@Param("userEmail") String userEmail, @Param("month") int month,
			@Param("year") int year);
}