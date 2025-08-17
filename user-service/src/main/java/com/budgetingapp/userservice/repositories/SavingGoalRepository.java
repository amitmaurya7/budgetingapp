package com.budgetingapp.userservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budgetingapp.userservice.entities.SavingGoal;

@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {

	Optional<List<SavingGoal>>findByUserEmail(String email);
	Optional<SavingGoal> findByUserEmailAndGoalName(String userEmail, String goalName);
}
