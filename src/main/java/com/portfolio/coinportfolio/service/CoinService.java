package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.model.Coin;
import com.portfolio.coinportfolio.model.Quotes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface CoinService {
    void updateCoin(Coin coin, Map<String, Quotes> coinMap);

    void getAllInfoAboutCoin(Coin coin);

    String getStringWithCurrentColorForPercent(int percent);

    String getStringWithCurrentColorForMoney(BigDecimal decimal);

    void getAllMinusCoinsAtPortfolio(Coin coin);

    void getAllPlusCoinsAtPortfolio(Coin coin);

    void getCoinsThatGaveMoreThan10Dollars(Coin coin);

    void getCoinsThatGaveMoreThan50ProfitPercents(Coin coin);

    int getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice);

    BigDecimal getProfitNumber(BigDecimal lastInvestmentCost, BigDecimal userInvestment);

    BigDecimal getLastInvestmentCost(BigDecimal userCoinVolume, BigDecimal actualPrice);

    BigDecimal getUserInvestmentNumber(BigDecimal userCoinVolume, BigDecimal userBuyPrice);

    BigDecimal getActualCoinPriceBySymbol(String symbol, Map<String, Quotes> coinHashMap);
}
