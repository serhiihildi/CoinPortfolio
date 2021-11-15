package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.impl.CoinServiceImpl;
import com.portfolio.coinportfolio.model.Quotes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CoinService implements CoinServiceImpl {

    static final Logger logger = LogManager.getLogger(CoinService.class.getName());

    @Override
    public BigDecimal getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice) {
            return ((actualPrice.subtract(userBuyPrice)).divide(userBuyPrice, 2, 2))
                    .multiply(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal getProfitNumber(BigDecimal lastInvestmentCost, BigDecimal userInvestment) {
        return lastInvestmentCost.subtract(userInvestment);
    }

    @Override
    public BigDecimal getLastInvestmentCost(BigDecimal userCoinVolume, BigDecimal actualPrice) {
        return userCoinVolume.multiply(actualPrice);
    }

    @Override
    public BigDecimal getUserInvestmentNumber(BigDecimal userCoinVolume, BigDecimal userBuyPrice) {
        return userBuyPrice.multiply(userCoinVolume);
    }

    @Override
    public BigDecimal getActualCoinPriceBySymbol(String symbol, Map<String, Quotes> coinHashMap) {
        if (coinHashMap.isEmpty()) {
            logger.error("Карта актуальных котировок пуста.");
        }

        return coinHashMap.entrySet().stream()
                .filter(x -> symbol.equals(x.getKey()))
                .findFirst()
                .orElse(null)
                .getValue()
                .getPrice();
    }
}
