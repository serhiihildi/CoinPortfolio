package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.CoinService;

import java.math.BigDecimal;
import java.util.HashMap;

public class Coin {

    private final String symbol;
    // BidDecimal с актуальной котировкой
    private BigDecimal actualPrice;
    // Цена покупки
    private final BigDecimal userBuyPrice;
    // Количество монет
    private final BigDecimal userCoinVolume;
    // Начальная стоимость актива
    private BigDecimal userInvestment;
    // Последняя стоимость инвестиций. Сколько на данный момент стоимость актива.
    private BigDecimal lastInvestmentCost;
    private final CoinService service = new CoinService();
    private HashMap<String, BigDecimal> coinHashMap;

    public Coin(String symbol, BigDecimal userBuyPrice, BigDecimal userCoinVolume, HashMap<String, BigDecimal> coinHashMap) {
        this.symbol = symbol;
        this.userBuyPrice = userBuyPrice;
        this.userCoinVolume = userCoinVolume;
        setCoinHashMap(coinHashMap);
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getUserBuyPrice() {
        return userBuyPrice;
    }

    public BigDecimal getUserCoinVolume() {
        return userCoinVolume;
    }

    public void setCoinHashMap(HashMap<String, BigDecimal> coinHashMap) {
        this.coinHashMap = coinHashMap;
    }

    public BigDecimal getActualPrice() {
        actualPrice = service.getActualCoinPriceBySymbol(symbol, coinHashMap);
        return actualPrice;
    }

    public BigDecimal getUserInvestment() {
        userInvestment = service.getUserInvestmentNumber(userCoinVolume, userBuyPrice);
        return userInvestment;
    }

    public BigDecimal getLastInvestmentCost() {
        lastInvestmentCost = service.getLastInvestmentCost(userCoinVolume, actualPrice);
        return lastInvestmentCost;
    }

    public BigDecimal getProfit() {
        // Прибыль
        return service.getProfitNumber(lastInvestmentCost, userInvestment);
    }

    public BigDecimal getProfitPercent() {
        // На сколько процентов изменился профит
        return service.getProfitPercent(actualPrice, userBuyPrice);
    }

    public void getCoinInfo (){
        System.out.println("Монета: " + getSymbol());
        System.out.println("Инвестиция: " + getUserInvestment());
        System.out.println("Цена покупки: " + getUserBuyPrice());
        System.out.println("Актуальная стоимость: " + getActualPrice());
        System.out.println("Колическтво монет: " + getUserCoinVolume());
        System.out.println("Актуальная стоимость актива: " + getLastInvestmentCost());
        System.out.println("Процент профита: " + getProfitPercent());
        System.out.println("Чистая прибыль: " + getProfit());
        System.out.println("_____________________");
    }
}
