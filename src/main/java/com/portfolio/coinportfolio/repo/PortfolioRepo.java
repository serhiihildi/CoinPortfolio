package com.portfolio.coinportfolio.repo;

import com.portfolio.coinportfolio.model.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioRepo extends CrudRepository<Portfolio, Long> {
    List<Portfolio> findByPortfolioName(String portfolioName);
}
