package com.analyticservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.analyticservice.dto.TransactionKafka;
import com.analyticservice.dto.Transactions;
import com.analyticservice.util.JwtTokenProvider;

@Service
public class AnalyticsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	

	 public static String categorize(String note) {
	        note = note.toLowerCase();
	        if (note.contains("walmart") || note.contains("supermarket")) {
	            return "Groceries";
	        } else if (note.contains("uber") || note.contains("ola")) {
	            return "Transport";
	        } else if (note.contains("electricity") || note.contains("power bill")) {
	            return "Utilities";
	        } else if (note.contains("netflix") || note.contains("spotify")) {
	            return "Entertainment";
	        }
	        return "Other";
	    }
	 
	 @KafkaListener(topics = "transactions", groupId = "analytics-group")
	 public void consume(TransactionKafka transaction) {
		 String token = jwtTokenProvider.generateToken("analytics-service");
		 HttpHeaders headers = new HttpHeaders();
		 headers.setBearerAuth(token);
		 
	     String category = AnalyticsService.categorize(transaction.getNote());
	     HttpEntity<Map<String, String>> entity =
	    		    new HttpEntity<>(Collections.singletonMap("category", category), headers);

	    		restTemplate.exchange(
	    		    "http://localhost:8083/api/transaction/" + transaction.getId() + "/category",
	    		    HttpMethod.PUT,
	    		    entity,
	    		    Void.class
	    		);
	 }
	 
	 public Map<String, Object> getMonthlySummary(String userEmail, int month, int year) {
		 
		 String token = jwtTokenProvider.generateToken("analytics-service");
		 HttpHeaders headers = new HttpHeaders();
		 headers.setBearerAuth(token);
		 
	     HttpEntity<String> entity = new HttpEntity<>( headers);
	        // 1. Fetch transactions from transaction-service
	     ResponseEntity<List<Transactions>> response = restTemplate.exchange(
	    		 "http://localhost:8083/api/transaction/expenses?userEmail=" + userEmail +
	    	        "&month=" + month + "&year=" + year,
	    	        HttpMethod.GET,
	    	        entity, 
	    	        new ParameterizedTypeReference<List<Transactions>>() {}
	    	    );
	        List<Transactions> transactions = response.getBody();

	        // 2. Group by category
	        Map<String, Double> categoryTotals = transactions.stream()
	                .collect(Collectors.groupingBy(
	                        Transactions::getCategory,
	                        Collectors.summingDouble(Transactions::getAmount)
	                ));

	        // 3. Calculate overall total
	        double total = categoryTotals.values().stream().mapToDouble(Double::doubleValue).sum();

	        // 4. Build response
	        Map<String, Object> result = new HashMap<>();
	        result.put("month", year + "-" + String.format("%02d", month));
	        result.put("userEmail", userEmail);
	        result.put("summary", categoryTotals);
	        result.put("total", total);

	        return result;
	    }

}
