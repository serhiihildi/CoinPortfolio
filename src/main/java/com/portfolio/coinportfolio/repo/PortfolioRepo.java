package com.portfolio.coinportfolio.repo;

import com.portfolio.coinportfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByPortfolioName(String portfolioName);
}
