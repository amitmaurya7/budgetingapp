package com.analyticservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/v1/user/**").permitAll() // register/login are public
	                .requestMatchers("/api/v1/saving-goal/**").authenticated() // saving goals need JWT
	                .anyRequest().authenticated()
	            )
	            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())); // validate JWT
	        return http.build();
	    }
}