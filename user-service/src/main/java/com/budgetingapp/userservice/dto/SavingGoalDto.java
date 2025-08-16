package com.budgetingapp.userservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingGoalDto {

	private String goalName;
	private double targetAmount;
	private String userEmail;
	private LocalDate localDate;
}
