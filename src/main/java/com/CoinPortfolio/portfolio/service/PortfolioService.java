package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Coin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class PortfolioService {

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * @throws Exception
     * Логика, позволяющая пользователю получить общую прибыль по портфелю
     */
    public BigDecimal getProfileProfit(ArrayList<Coin> coinArrayList) throws Exception {
        BigDecimal profitSum = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (Coin value : coinArrayList) {
                BigDecimal profit = value.getProfit();
                profitSum = profit.add(profitSum).plus(new MathContext(4, RoundingMode.HALF_UP));
            }
        } else throw new Exception("ArrayList is empty.");
        return profitSum;
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * @throws Exception
     * Логика, позволяющая пользователю получить значение начальных инвестиций в портфель
     */
    public BigDecimal getUserInvestmentNumber(ArrayList<Coin> coinArrayList) throws Exception {
        BigDecimal userInvestmentSum = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (Coin value : coinArrayList) {
                BigDecimal userInvestment = value.getUserInvestment();
                userInvestmentSum = userInvestment.add(userInvestmentSum).plus(new MathContext(4, RoundingMode.HALF_UP));
            }
        } else throw new Exception("ArrayList is empty.");
        return userInvestmentSum;
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * @throws Exception
     * Логика, позволяющая пользователю получить актуальную стоимость портфеля
     */
    public BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Coin> coinArrayList) throws Exception {
        BigDecimal sumOfLastInvestmentCost = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (Coin value : coinArrayList) {
                BigDecimal lastInvestmentCost = value.getLastInvestmentCost();
                sumOfLastInvestmentCost = lastInvestmentCost
                        .add(sumOfLastInvestmentCost)
                        .plus(new MathContext(4,RoundingMode.HALF_UP));
            }
        } else throw new Exception("ArrayList is empty.");
        return sumOfLastInvestmentCost;
    }

    /**
     * void
     * @param userPortfolio
     * Печатает все Coin содержадиеся в коллекции
     */
    public void getAllCoinWhichAreContainedAtList(ArrayList<Coin> userPortfolio) {
        for (Coin coin : userPortfolio) {
            System.out.println(coin.toString());
        }
    }

    public void getProfitOnEveryCoin(ArrayList<Coin> userPortfolio) {
        System.out.println("Прибыль по монетам:");
        for (Coin coin : userPortfolio) {
//            coin.toStringCoinProfit();
        }
    }

    public Coin addCoinToCustomPortfolio(String symbol,
                                         BigDecimal userByuPrice,
                                         BigDecimal userCoinVolume,
                                         HashMap<String, BigDecimal> coinHashMap) {
        return new Coin(
                symbol,
                userByuPrice,
                userCoinVolume,
                coinHashMap
        );
    }
}
