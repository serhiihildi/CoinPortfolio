package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserServiceImpl {

    Portfolio createNewUserPortfolio(String name);
    ArrayList<Portfolio> addNewUserPortfolioToList(Portfolio portfolio);
    BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio);
}
