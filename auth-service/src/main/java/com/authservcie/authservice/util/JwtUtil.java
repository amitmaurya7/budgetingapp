package com.authservcie.authservice.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "PWPwZO7nk3wOaFUf+YXFL4UC1G8uLstVksxbGyE95l4=";

    public String generateToken(String email) {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
