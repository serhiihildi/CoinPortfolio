package com.portfolio.coinportfolio.repo;

import com.portfolio.coinportfolio.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinRepo extends JpaRepository<Coin, Long> {
    List<Coin> findByCoinSymbol(String symbol);
}
