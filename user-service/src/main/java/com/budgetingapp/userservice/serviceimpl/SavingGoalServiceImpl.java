package com.budgetingapp.userservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetingapp.userservice.dto.SavingGoalDto;
import com.budgetingapp.userservice.entities.SavingGoal;
import com.budgetingapp.userservice.exception.UserNotExistException;
import com.budgetingapp.userservice.repositories.SavingGoalRepository;
import com.budgetingapp.userservice.service.SavingGoalService;

@Service
public class SavingGoalServiceImpl implements SavingGoalService {

	@Autowired
	private SavingGoalRepository savingGoalRepository;

	@Override
	public SavingGoal createGoal(SavingGoalDto savingGoalDto) {
		SavingGoal savingGoal = new SavingGoal();
		savingGoal.setGoalName(savingGoalDto.getGoalName());
		savingGoal.setTargetAmount(savingGoalDto.getTargetAmount());
		savingGoal.setUserEmail(savingGoalDto.getUserEmail());
		savingGoal.setDeadLine(savingGoalDto.getLocalDate());
		savingGoal = savingGoalRepository.save(savingGoal);
		return savingGoal;
	}

	@Override
	public List<SavingGoal> getGoal(String email) {
		List<SavingGoal> savingGoals = savingGoalRepository.findByUserEmail(email)
				.orElseThrow(() -> new UserNotExistException("user with email id: " + email + " is not present"));
		return savingGoals;
	}

	@Override
	public SavingGoal getSavingGoal(String email, String goalName) {
		return savingGoalRepository.findByUserEmailAndGoalName(email, goalName)
				.orElseThrow(() -> new UserNotExistException(
						"Saving goal with name '" + goalName + "' not found for user " + email));
	}

}
