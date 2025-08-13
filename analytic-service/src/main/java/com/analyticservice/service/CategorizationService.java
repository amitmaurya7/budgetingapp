package com.analyticservice.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.analyticservice.dto.TransactionKafka;

@Service
public class CategorizationService {
	
	@Autowired
	private RestTemplate restTemplate;

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
	     String category = CategorizationService.categorize(transaction.getNote());
	     restTemplate.put(
	         "http://localhost:8083/api/transaction/" + transaction.getId() + "/category",
	         Collections.singletonMap("category", category)
	     );
	 }

}
