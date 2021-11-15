package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.impl.UserServiceImpl;
import com.portfolio.coinportfolio.model.Portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UserService implements UserServiceImpl {

    private ArrayList<Portfolio> userPortfolioList;

    @Override
    public Portfolio createNewUserPortfolio(String name) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(name);
        return portfolio;
    }

    @Override
    public ArrayList<Portfolio> addNewUserPortfolioToList(Portfolio portfolio) {
        if (userPortfolioList == null) {
            userPortfolioList = new ArrayList<>();
        }
        userPortfolioList.add(portfolio);
        return userPortfolioList;
    }

    @Override
    public BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio)  {
        BigDecimal profileProfitNumber = BigDecimal.ZERO;
        for (Portfolio value : userPortfolio) {
            value.setGlobalInfo();
            profileProfitNumber = profileProfitNumber.add(value.getProfileProfitNumber());
        }
        return profileProfitNumber;
    }
}
