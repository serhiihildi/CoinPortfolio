package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.PortfolioService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio {

    private HashMap<String, BigDecimal> priceMap;
    private PortfolioService portfolioService;
    private ArrayList<Coin> userCoinList;
    private Price price;
    private User user;

    private String name;
    private BigDecimal userInvestmentNumber;
    private BigDecimal profileProfitNumber;
    private BigDecimal currentUserPortfolioInvestmentNumber;

    public Portfolio() {
        if (getPriceMap() == null) {
            setPrice(new Price());
            setPriceMap(getPrice().getPrice());
        }
        setUserCoinList(new ArrayList<>());
        setPortfolioService(new PortfolioService());
    }

    public void addCoinToPortfolio(String symbol, BigDecimal userByuPrice,
                                   BigDecimal userCoinVolume) {
        if (getPriceMap() != null) {
            if (getUserCoinList() == null) {
                setUserCoinList(new ArrayList<>());
            }
            getUserCoinList().add(getPortfolioService().addCoinToCustomPortfolio(symbol, userByuPrice, userCoinVolume, getPriceMap()));
        }
        setUserCoinList(getUserCoinList());
    }

    public void setGlobalInfo() throws Exception {
        setUserInvestmentNumber(getPortfolioService().getUserInvestmentNumber(getUserCoinList()));
        setCurrentUserPortfolioInvestmentNumber(getPortfolioService().getCurrentUserPortfolioInvestmentNumber(getUserCoinList()));
        setProfileProfitNumber(getPortfolioService().getProfileProfit(getUserCoinList()));
    }

    public void getGlobalInfo() {
        try {
            setGlobalInfo();
        } catch (Exception e) {
            // TODO:
            e.printStackTrace();
        }
        System.out.printf("Начальная цена портфеля: $%s\n" +
                        "Актуальная стоимость портфеля: $%s\n" +
                        "Прибыль: $%s\n",
                getUserInvestmentNumber(),
                getCurrentUserPortfolioInvestmentNumber(),
                getProfileProfitNumber()
        );
        System.out.println("____________________");
    }

    public PortfolioService getPortfolioService() {
        return portfolioService;
    }

    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Coin> getUserCoinList() {
        return userCoinList;
    }

    public void setUserCoinList(ArrayList<Coin> userCoinList) {
        this.userCoinList = userCoinList;
    }

    public HashMap<String, BigDecimal> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(HashMap<String, BigDecimal> priceMap) {
        this.priceMap = priceMap;
    }

    public BigDecimal getUserInvestmentNumber() {
        return userInvestmentNumber;
    }

    public void setUserInvestmentNumber(BigDecimal userInvestmentNumber) {
        this.userInvestmentNumber = userInvestmentNumber;
    }

    public BigDecimal getProfileProfitNumber() {
        return profileProfitNumber;
    }

    public void setProfileProfitNumber(BigDecimal profileProfitNumber) {
        this.profileProfitNumber = profileProfitNumber;
    }

    public BigDecimal getCurrentUserPortfolioInvestmentNumber() {
        return currentUserPortfolioInvestmentNumber;
    }

    public void setCurrentUserPortfolioInvestmentNumber(BigDecimal currentUserPortfolioInvestmentNumber) {
        this.currentUserPortfolioInvestmentNumber = currentUserPortfolioInvestmentNumber;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Биржа: " + getName() + "\n" + userCoinList;
    }

}
