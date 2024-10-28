package com.example.cryptotrading.model;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    private Long userId;
    private double balance = 50000.00;  // Initial balance in USDT
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

    
}