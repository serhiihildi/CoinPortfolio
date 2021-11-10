package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.impl.PortfolioServiceImpl;
import com.CoinPortfolio.portfolio.model.Quotes;
import com.CoinPortfolio.portfolio.model.UserCoin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class PortfolioService implements PortfolioServiceImpl {

    static final Logger logger = LogManager.getLogger(PortfolioService.class.getName());

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the total return on the portfolio
     */
    @Override
    public BigDecimal getProfileProfit(ArrayList<UserCoin> coinArrayList) {
        BigDecimal profitSum = BigDecimal.ZERO;
        if (coinArrayList.isEmpty()) {
            logger.error("Failed to get (as a passed parameter) the list of available coins from the user.");
        }
        for (UserCoin value : coinArrayList) {
            BigDecimal profit = value.getProfit();
            profitSum = profit.add(profitSum).plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return profitSum;
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the value of the initial investment in the portfolio
     */
    @Override
    public BigDecimal getUserInvestmentNumber(ArrayList<UserCoin> coinArrayList) {
        if (coinArrayList.isEmpty()) {
            logger.error("Failed to get (as a passed parameter) the list of available coins from the user.");
        }
        BigDecimal userInvestmentSum = BigDecimal.ZERO;
        for (UserCoin value : coinArrayList) {
            BigDecimal userInvestment = value.getUserInvestment();
            userInvestmentSum = userInvestment.add(userInvestmentSum).plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return userInvestmentSum;
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the actual value of the portfolio
     */
    @Override
    public BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<UserCoin> coinArrayList) {
        if (coinArrayList.isEmpty()) {
            logger.error("Failed to get (as a passed parameter) the list of available coins from the user.");
        }
        BigDecimal sumOfLastInvestmentCost = BigDecimal.ZERO;
        for (UserCoin value : coinArrayList) {
            BigDecimal lastInvestmentCost = value.getLastInvestmentCost();
            sumOfLastInvestmentCost = lastInvestmentCost
                    .add(sumOfLastInvestmentCost)
                    .plus(new MathContext(4,RoundingMode.HALF_UP));
        }
        return sumOfLastInvestmentCost;
    }

    /**
     * void
     * @param coinArrayList
     * Печатает все Coin содержадиеся в коллекции
     */
    @Override
    public void getAllCoinWhichAreContainedAtList(ArrayList<UserCoin> coinArrayList) {
        coinArrayList.forEach(System.out::println);
    }

    /**
     *
     * @param symbol
     * @param userByuPrice
     * @param userCoinVolume
     * @param coinMap
     * @return UserCoin
     * Logic allows the user to add their own Coin list to their Portfolio
     */
    @Override
    public UserCoin addCoinToCustomPortfolio(String symbol, BigDecimal userByuPrice, BigDecimal userCoinVolume, Map<String, Quotes> coinMap) {
        return new UserCoin(
                symbol, userByuPrice,
                userCoinVolume, coinMap
        );
    }


}
