package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoCurrency, String> {
	
}
