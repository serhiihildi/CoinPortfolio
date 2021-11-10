package com.CoinPortfolio.portfolio.impl;

import com.CoinPortfolio.portfolio.model.Portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserServiceImpl {

    Portfolio createNewUserPortfolio(String name);
    ArrayList<Portfolio> addNewUserPortfolioToList(Portfolio portfolio);
    BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio);
}
