package com.petProject.portfolio.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MathService {
    private HashMap<String, BigDecimal> coinHashMap;

    public HashMap<String, BigDecimal> getCoinHashMap() {
        coinHashMap = getCoinMap();
        return coinHashMap;
    }

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

    public BigDecimal getActualCoinPriceBySymbol(String symbol) {
        BigDecimal value = null;
        for (Map.Entry<String, BigDecimal> entry : coinHashMap.entrySet()) {
            if (entry.getKey().equals(symbol)) {
                value = entry.getValue();
            }
        }
        return value;
    }

    private HashMap<String, BigDecimal> getCoinMap() {
        coinHashMap = new GetCurrentCoinPrice().getCoinHashMap();
        return coinHashMap;
    }
}
