package com.analyticservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

