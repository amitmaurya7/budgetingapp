package com.analyticservice.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.analyticservice.dto.TransactionKafka;
import com.analyticservice.util.JwtTokenProvider;

@Service
public class CategorizationService {
	
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
		 
	     String category = CategorizationService.categorize(transaction.getNote());
	     HttpEntity<Map<String, String>> entity =
	    		    new HttpEntity<>(Collections.singletonMap("category", category), headers);

	    		restTemplate.exchange(
	    		    "http://localhost:8083/api/transaction/" + transaction.getId() + "/category",
	    		    HttpMethod.PUT,
	    		    entity,
	    		    Void.class
	    		);
	 }

}
