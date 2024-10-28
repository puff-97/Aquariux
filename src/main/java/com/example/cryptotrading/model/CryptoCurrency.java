package com.example.cryptotrading.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class CryptoCurrency {
	  
	@Id
	private String symbol;
	private double bidPrice;
	private double askPrice;

	public CryptoCurrency() {
		super();
		this.symbol = symbol;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

}