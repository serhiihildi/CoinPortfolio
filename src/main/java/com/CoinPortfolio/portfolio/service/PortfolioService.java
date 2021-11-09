package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Coin;
import com.CoinPortfolio.portfolio.model.UserCoin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class PortfolioService {

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * @throws Exception
     * Логика, позволяющая пользователю получить общую прибыль по портфелю
     */
    public BigDecimal getProfileProfit(ArrayList<UserCoin> coinArrayList) throws Exception {
        BigDecimal profitSum = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (UserCoin value : coinArrayList) {
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
    public BigDecimal getUserInvestmentNumber(ArrayList<UserCoin> coinArrayList) throws Exception {
        BigDecimal userInvestmentSum = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (UserCoin value : coinArrayList) {
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
    public BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<UserCoin> coinArrayList) throws Exception {
        BigDecimal sumOfLastInvestmentCost = BigDecimal.ZERO;
        if (!coinArrayList.isEmpty()) {
            for (UserCoin value : coinArrayList) {
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
    public void getAllCoinWhichAreContainedAtList(ArrayList<UserCoin> userPortfolio) {
        for (UserCoin coin : userPortfolio) {
            System.out.println(coin.toString());
        }
    }

    public UserCoin addCoinToCustomPortfolio(String symbol,
                                             BigDecimal userByuPrice,
                                             BigDecimal userCoinVolume,
                                             Map<String, Coin> coinMap) {
        return new UserCoin(
                symbol,
                userByuPrice,
                userCoinVolume,
                coinMap
        );
    }
}
