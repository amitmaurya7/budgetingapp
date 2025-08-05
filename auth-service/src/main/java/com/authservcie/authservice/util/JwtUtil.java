package com.authservcie.authservice.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "PWPwZO7nk3wOaFUf+YXFL4UC1G8uLstVksxbGyE95l4=";
	SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	
	public String generateToken(String email) {
		return Jwts.builder()
				 .setSubject(email)
			     .setIssuedAt(new Date(System.currentTimeMillis()))
			     .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
			     .signWith(key, SignatureAlgorithm.HS256)
			     .compact();
	}
}
