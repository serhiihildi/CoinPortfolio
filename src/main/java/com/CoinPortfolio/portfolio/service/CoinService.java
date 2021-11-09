package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Coin;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CoinService {

    public BigDecimal getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice) {
            return ((actualPrice.subtract(userBuyPrice))
                    .divide(userBuyPrice, 2,2))
                    .multiply(new BigDecimal(100));
    }

    public BigDecimal getProfitNumber(BigDecimal lastInvestmentCost, BigDecimal userInvestment) {
        return lastInvestmentCost.subtract(userInvestment);
    }

    public BigDecimal getLastInvestmentCost(BigDecimal userCoinVolume, BigDecimal actualPrice) {
        return userCoinVolume.multiply(actualPrice);
    }

    public BigDecimal getUserInvestmentNumber(BigDecimal userCoinVolume, BigDecimal userBuyPrice) {
        return userBuyPrice.multiply(userCoinVolume);
    }

    public BigDecimal getActualCoinPriceBySymbol(String symbol, Map<String, Coin> coinHashMap) {
        return Objects.requireNonNull(coinHashMap.entrySet().stream()
                .filter(x -> symbol.equals(x.getKey()))
                .findFirst()
                .orElse(null))
                .getValue()
                .getPrice();
    }
}
