package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Portfolio;
import com.portfolio.coinportfolio.model.Quotes;
import com.portfolio.coinportfolio.model.Coin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public interface PortfolioServiceImpl {

    Map<String, Quotes> getActualQuotes();

    void setGlobalInfo(Portfolio portfolio);
    void updateCoinList(Portfolio portfolio);
    void getGlobalInfo(String name, Portfolio portfolio);
    void getAllCoinInfoAtPortfolio(String name, Portfolio portfolio);
    void getAllMinusCoinsAtPortfolio(String name, Portfolio portfolio);
    void getAllPlusCoinsAtPortfolio(String name, Portfolio portfolio);
    void getCoinsThatGaveMoreThan10Dollars(String name, Portfolio portfolio);
    void getCoinsThatGaveMoreThan50ProfitPercents(String name, Portfolio portfolio);


    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the total return on the portfolio
     */
    BigDecimal getProfileProfit(ArrayList<Coin> coinArrayList);

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the value of the initial investment in the portfolio
     */
    BigDecimal getUserInvestmentNumber(ArrayList<Coin> coinArrayList);

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the actual value of the portfolio
     */
    BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Coin> coinArrayList);

}
