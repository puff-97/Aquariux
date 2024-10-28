package com.example.cryptotrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.example.cryptotrading.repository")
public class CryptotradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptotradingApplication.class, args);
	}
}
