package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserServiceImpl {

    BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio);
    BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Portfolio> userPortfolio);
    void takeTotalUserProfit(ArrayList<Portfolio> userPortfolio);
}
