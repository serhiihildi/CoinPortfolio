package com.CoinPortfolio.portfolio.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    public BigDecimal getActualCoinPriceBySymbol(String symbol, HashMap<String, BigDecimal> coinHashMap) {
        BigDecimal value = null;
        for (Map.Entry<String, BigDecimal> entry : coinHashMap.entrySet()) {
            if (entry.getKey().equals(symbol)) {
                value = entry.getValue();
            }
        }
        return value;
    }
}
