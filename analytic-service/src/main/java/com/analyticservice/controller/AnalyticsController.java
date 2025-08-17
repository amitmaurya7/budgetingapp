package com.analyticservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analyticservice.dto.GoalProgressDto;
import com.analyticservice.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics/")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<String, Object>> getMonthlySummary(
            @RequestParam String userEmail,
            @RequestParam int month,
            @RequestParam int year) {

        Map<String, Object> summary = analyticsService.getMonthlySummary(userEmail, month, year);
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/goal-progress")
    public ResponseEntity<GoalProgressDto> getGoalProgress(@RequestParam int month, @RequestParam int year, @RequestParam String goalName, @AuthenticationPrincipal Jwt principal){
    	String userEmail = principal.getSubject();
    	String token = principal.getTokenValue();
    	GoalProgressDto goalProgressDto = analyticsService.trackGoalProgress(userEmail, month, year, goalName, token);
    	return ResponseEntity.status(HttpStatus.OK).body(goalProgressDto);
    }
}

