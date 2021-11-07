package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UserService {

    private ArrayList<Portfolio> userPortfolioList;

    public Portfolio createNewUserPortfolio(String name) throws Exception {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(name);
        return portfolio;
    }

    public ArrayList<Portfolio> addNewUserPortfolioToList(Portfolio portfolio) {
        if (userPortfolioList == null) {
            userPortfolioList = new ArrayList<>();
        }
        userPortfolioList.add(portfolio);
        return userPortfolioList;
    }

    public BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio) throws Exception {
        BigDecimal profileProfitNumber = BigDecimal.ZERO;
        for (Portfolio value : userPortfolio) {
            value.setGlobalInfo();
            profileProfitNumber = profileProfitNumber.add(value.getProfileProfitNumber());
        }
        return profileProfitNumber;
    }
}
