package com.analyticservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingGoal {
    private Long id;
    private String goalName;
    private Double targetAmount;
    private LocalDate deadLine;
    private String userEmail;
}

