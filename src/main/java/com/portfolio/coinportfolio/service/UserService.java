package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.model.Portfolio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public interface UserService {
    BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio);
    BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Portfolio> userPortfolio);
    void takeTotalUserProfit(ArrayList<Portfolio> userPortfolio);
}
