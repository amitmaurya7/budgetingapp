package com.analyticservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalProgressDto {

	private double goalAmount;
	private double savingAmount;
	private double progressPercentage;
	private boolean goalAchieved;
}
