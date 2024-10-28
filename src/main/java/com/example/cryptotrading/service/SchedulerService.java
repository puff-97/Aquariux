package com.example.cryptotrading.service;


import com.example.cryptotrading.model.CryptoCurrency;
import com.example.cryptotrading.repository.CryptoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SchedulerService {

	@Autowired
    private CryptoRepository cryptoRepository;

    @Scheduled(fixedRate = 10000) // 10 seconds
    public void updateCryptoPrices() {
    	 try {
             String url = "https://api.huobi.pro/market/tickers";
             RestTemplate restTemplate = new RestTemplate();
             ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

             if (response.getStatusCode().is2xxSuccessful()) {
                 List<Map<String, Object>> tickers = (List<Map<String, Object>>) response.getBody().get("data");
                 if (tickers != null) {
                     for (Map<String, Object> ticker : tickers) {
                         String symbol = (String) ticker.get("symbol");
                         double bidPrice = Double.parseDouble(ticker.get("bid").toString());
                         double askPrice = Double.parseDouble(ticker.get("ask").toString());

                         // Save or update crypto data in H2
                         CryptoCurrency crypto = new CryptoCurrency();
                         crypto.setSymbol(symbol);
                         crypto.setBidPrice(bidPrice);
                         crypto.setAskPrice(askPrice);

                         cryptoRepository.save(crypto);
                     }
                     System.out.println("Prices updated successfully");
                 }
             } else {
                 System.err.println("Failed to fetch data: " + response.getStatusCode());
             }
         } catch (Exception e) {
             System.err.println("Exception in price update: " + e.getMessage());
         }
     }
    
}