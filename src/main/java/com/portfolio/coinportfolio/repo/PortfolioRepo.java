package com.portfolio.coinportfolio.repo;

import com.portfolio.coinportfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByPortfolioName(String portfolioName);
}
