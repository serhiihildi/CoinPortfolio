package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.impl.CoinServiceImpl;
import com.CoinPortfolio.portfolio.model.Quotes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

public class CoinService implements CoinServiceImpl {

    static final Logger logger = LogManager.getLogger(CoinService.class.getName());

    @Override
    public BigDecimal getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice) {
            return ((actualPrice.subtract(userBuyPrice))
                    .divide(userBuyPrice, 2,2))
                    .multiply(new BigDecimal(100));
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
