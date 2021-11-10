package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.CoinService;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class UserCoin {

    private CoinService service;
    private Map<String, Quotes> coinMapWithActualPrice;
    private String symbol;
    private BigDecimal actualPrice;
    private BigDecimal userBuyPrice;
    private BigDecimal userCoinVolume;
    private BigDecimal userInvestment;
    private BigDecimal lastInvestmentCost;
    private BigDecimal profitPercent;
    private BigDecimal profit;

    public UserCoin(String symbol, BigDecimal userBuyPrice, BigDecimal userCoinVolume, Map<String, Quotes> coinMap) {
        setService(new CoinService());
        setCoinMapWithActualPrice(coinMap);
        setSymbol(symbol);
        setUserBuyPrice(userBuyPrice);
        setUserCoinVolume(userCoinVolume);
        setActualPrice(getService().getActualCoinPriceBySymbol(getSymbol(), getCoinMapWithActualPrice()));
        setUserInvestment(getService().getUserInvestmentNumber(getUserCoinVolume(), getUserBuyPrice()));
        setLastInvestmentCost(getService().getLastInvestmentCost(getUserCoinVolume(), getActualPrice()));
        setProfit(getService().getProfitNumber(getLastInvestmentCost(), getUserInvestment()));
        setProfitPercent(getService().getProfitPercent(getActualPrice(), getUserBuyPrice()));
    }

    @Override
    public String toString() {
        return "\nМонета: " + getSymbol() + '\n' +
                "Инвестиция: " + getUserInvestment() + '\n' +
                "Цена покупки: " + getUserBuyPrice() + '\n' +
                "Актуальная стоимость: " + getActualPrice() + '\n' +
                "Колическтво монет: " + getUserCoinVolume() + '\n' +
                "Актуальная стоимость актива: " + getLastInvestmentCost() + '\n' +
                "Процент профита: " + getProfitPercent() + '\n' +
                "Чистая прибыль: " + getProfit() + '\n' +
                "_____________________";
    }
}
