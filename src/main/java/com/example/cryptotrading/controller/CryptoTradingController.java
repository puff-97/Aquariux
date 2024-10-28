package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.Transaction;
import com.example.cryptotrading.model.Wallet;
import com.example.cryptotrading.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trading")
public class CryptoTradingController {

    @Autowired
    private TradingService tradingService;

    @GetMapping("/wallet/{userId}")
    public Wallet getWalletBalance(@PathVariable Long userId) {
        return tradingService.getUserWalletBalance(userId);
    }

    @GetMapping("/history/{userId}")
    public List<Transaction> getTradingHistory(@PathVariable Long userId) {
        return tradingService.getUserTradingHistory(userId);
    }

    @PostMapping("/trade")
    public String trade(@RequestParam Long userId, @RequestParam String symbol,
                        @RequestParam double amount, @RequestParam String type) {
        return tradingService.trade(userId, symbol, amount, type);
    }
}
