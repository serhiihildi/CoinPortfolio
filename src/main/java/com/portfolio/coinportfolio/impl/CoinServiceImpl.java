package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Quotes;

import java.math.BigDecimal;
import java.util.Map;

public interface CoinServiceImpl {
    BigDecimal getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice);
    BigDecimal getProfitNumber(BigDecimal lastInvestmentCost, BigDecimal userInvestment);
    BigDecimal getLastInvestmentCost(BigDecimal userCoinVolume, BigDecimal actualPrice);
    BigDecimal getUserInvestmentNumber(BigDecimal userCoinVolume, BigDecimal userBuyPrice);
    BigDecimal getActualCoinPriceBySymbol(String symbol, Map<String, Quotes> coinHashMap);
}
