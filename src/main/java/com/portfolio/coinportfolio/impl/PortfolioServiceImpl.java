package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Quotes;
import com.portfolio.coinportfolio.model.Coin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public interface PortfolioServiceImpl {

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


    /**
     * void
     * @param coinArrayList
     * Печатает все Coin содержадиеся в коллекции
     */
    void getAllCoinWhichAreContainedAtList(ArrayList<Coin> coinArrayList);

    /**
     * @param symbol
     * @param userByuPrice
     * @param userCoinVolume
     * @param coinMap
     * @return UserCoin
     * Logic allows the user to add their own Coin list to their Portfolio
     */
    Coin addCoinToCustomPortfolio(String symbol, BigDecimal userByuPrice,
                                  BigDecimal userCoinVolume, Map<String, Quotes> coinMap);
}
