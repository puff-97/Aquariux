package com.example.cryptotrading.service;

import com.example.cryptotrading.model.CryptoCurrency;
import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.Wallet;
import com.example.cryptotrading.repository.CryptoRepository;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TradingService {

	 @Autowired
	    private WalletRepository walletRepository;

	    @Autowired
	    private CryptoRepository cryptoRepository;

	    @Autowired
	    private TransactionRepository transactionRepository;

	    public List<Transaction> getUserTradingHistory(Long userId) {
	        return transactionRepository.findByUserId(userId);
	    }

	    public Wallet getUserWalletBalance(Long userId) {
	        return walletRepository.findById(userId).orElse(null);
	    }

	    public String trade(Long userId, String symbol, double amount, String type) {
	        Optional<Wallet> walletOpt = walletRepository.findById(userId);
	        Optional<CryptoCurrency> cryptoOpt = cryptoRepository.findById(symbol);

	        if (walletOpt.isEmpty() || cryptoOpt.isEmpty()) return "Invalid data";

	        Wallet wallet = walletOpt.get();
	        CryptoCurrency crypto = cryptoOpt.get();
	        double price = (type.equals("BUY") ? crypto.getAskPrice() : crypto.getBidPrice());
	        double total = amount * price;

	        if (type.equals("BUY") && wallet.getBalance() >= total) {
	            wallet.setBalance(wallet.getBalance() - total);
	            walletRepository.save(wallet);
	            transactionRepository.save(new Transaction(userId, symbol, amount, type, price));
	            return "Buy successful";
	        } else if (type.equals("SELL")) {
	            wallet.setBalance(wallet.getBalance() + total);
	            walletRepository.save(wallet);
	            transactionRepository.save(new Transaction(userId, symbol, amount, type, price));
	            return "Sell successful";
	        }
	        return "Insufficient balance";
	    }
	}
