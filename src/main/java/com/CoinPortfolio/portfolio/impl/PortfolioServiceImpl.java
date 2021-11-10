package com.CoinPortfolio.portfolio.impl;

import com.CoinPortfolio.portfolio.model.Quotes;
import com.CoinPortfolio.portfolio.model.UserCoin;

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
    BigDecimal getProfileProfit(ArrayList<UserCoin> coinArrayList);

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the value of the initial investment in the portfolio
     */
    BigDecimal getUserInvestmentNumber(ArrayList<UserCoin> coinArrayList);

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the actual value of the portfolio
     */
    BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<UserCoin> coinArrayList);


    /**
     * void
     * @param coinArrayList
     * Печатает все Coin содержадиеся в коллекции
     */
    void getAllCoinWhichAreContainedAtList(ArrayList<UserCoin> coinArrayList);

    /**
     * @param symbol
     * @param userByuPrice
     * @param userCoinVolume
     * @param coinMap
     * @return UserCoin
     * Logic allows the user to add their own Coin list to their Portfolio
     */
    UserCoin addCoinToCustomPortfolio(String symbol, BigDecimal userByuPrice,
                                        BigDecimal userCoinVolume, Map<String, Quotes> coinMap);
}
