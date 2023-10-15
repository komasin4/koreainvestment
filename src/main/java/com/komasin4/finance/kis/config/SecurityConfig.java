package com.komasin4.finance.kis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
			// ...
			.authorizeHttpRequests(authorize -> authorize                                  
//	            .dispatcherTypeMatchers(FORWARD, ERROR).permitAll() 
//				.requestMatchers("/static/**", "/signup", "/about").permitAll()         
//				.requestMatchers("/admin/**").hasRole("ADMIN")                             
//				.requestMatchers("/db/**").access(allOf(hasAuthority('db'), hasRole('ADMIN')))   
				.requestMatchers("/static/**", "/signup", "/about").permitAll()         
				.requestMatchers("/helloWorld/**", "/api/**").permitAll()         
				.anyRequest().denyAll()                                                
			);

		return http.build();
	}

}
