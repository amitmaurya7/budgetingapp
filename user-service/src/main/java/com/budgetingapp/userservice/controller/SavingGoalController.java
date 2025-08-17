package com.budgetingapp.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budgetingapp.userservice.dto.SavingGoalDto;
import com.budgetingapp.userservice.entities.SavingGoal;
import com.budgetingapp.userservice.service.SavingGoalService;

@RestController
@RequestMapping("api/v1/saving-goal")
public class SavingGoalController {

	@Autowired
	private SavingGoalService savingGoalService;
	
	@PostMapping("/create")
	public ResponseEntity<SavingGoal>createGoal(@RequestBody SavingGoalDto savingGoalDto){
		SavingGoal savingGoal = savingGoalService.createGoal(savingGoalDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savingGoal);
	}
	
	@GetMapping("/get-saving-goal")
	public ResponseEntity<List<SavingGoal>>getSavingGoal( @AuthenticationPrincipal Jwt principal){
		String email = principal.getSubject();
		List<SavingGoal> savingGoals = savingGoalService.getGoal(email);
		return ResponseEntity.status(HttpStatus.FOUND).body(savingGoals);
	}
	
	@GetMapping("/goal")
	public ResponseEntity<SavingGoal>getSavingGoal( @RequestParam String goalName,@AuthenticationPrincipal Jwt principal){
		String email = principal.getSubject();
		SavingGoal savingGoals = savingGoalService.getSavingGoal(email, goalName);
		return ResponseEntity.ok(savingGoals);
	}
}
