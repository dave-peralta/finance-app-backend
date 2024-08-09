package com.financeapp.financeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FinanceppApplication {

	// TODO: 23/07/2024
//	1. Security and Error Handling
//	2. Unit and Integration Testing
//	3. Add error handling; focus on this; should be meaningful status code for each error and error message

// Push backend to Docker after backend is finished
// To test docker, send request to docker url instead of localhost
//	https://github.com/priya4991/auth-service for error handling

	// Add web security and endpoints especially authorization configurations
		// - only register is open, the rest should be secure
		// - unit testing
		// - integration testing

	// Add error handling
	// Centralized logging -- research

	// when creating new user,

	public static void main(String[] args) {
		SpringApplication.run(FinanceppApplication.class, args);
	}

}
