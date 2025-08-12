package com.transaction.transactionservice.config;

import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

	 @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")
	    private String secretKey;

	 @Bean
	 public JwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secret) {
	     byte[] decodedKey = Base64.getDecoder().decode(secret);
	     SecretKeySpec key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
	     return NimbusJwtDecoder.withSecretKey(key).build();
	 }
}
