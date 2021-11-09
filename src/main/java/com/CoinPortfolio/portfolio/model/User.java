package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;

public class User {

    private UserService service;
    private Portfolio portfolio;
    private ArrayList<Portfolio> userPortfolioList;
    private String name;
    private String password;
    private BigDecimal userProfit;

    public User() {
        setName("Новый юзер");
        setService(new UserService());
        setPortfolio(new Portfolio());
        setUserPortfolioList(new ArrayList<>());
    }

    public void setUserProfit(BigDecimal userProfit) {
        this.userProfit = userProfit;
    }

    public BigDecimal getUserProfit() {
        return userProfit;
    }

    public void takeUserProfit() throws Exception {
        BigDecimal userProfitSum = getService().getUserProfitSum(getUserPortfolioList());
        setUserProfit(userProfitSum);
        System.out.printf("Общая прибыль пользователя: $%s\n", getUserProfit());
    }

    public Portfolio createNewUserPortfolio(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Name field is empty.");
        }
        if (service == null) {
            service = new UserService();
        }
        return service.createNewUserPortfolio(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public ArrayList<Portfolio> getUserPortfolioList() {
        return userPortfolioList;
    }

    public void setUserPortfolioList(ArrayList<Portfolio> userPortfolioList) {
        this.userPortfolioList = userPortfolioList;
    }
}