package com.budgetingapp.userservice.service;

import java.util.List;

import com.budgetingapp.userservice.dto.SavingGoalDto;
import com.budgetingapp.userservice.entities.SavingGoal;

public interface SavingGoalService {

	public SavingGoal createGoal(SavingGoalDto savingGoalDto);
	public List<SavingGoal> getGoal(String email);
}
