package com.petProject.portfolio.model;

import com.petProject.portfolio.service.MathService;

import java.math.BigDecimal;
import java.util.HashMap;

public class Coin {

    private String symbol;
    // BidDecimal с актуальной котировкой
    private BigDecimal actualPrice;
    // Цена покупки
    private BigDecimal userBuyPrice;
    // Количество монет
    private BigDecimal userCoinVolume;
    // Начальная стоимость актива
    private BigDecimal userInvestment;
    // Последняя стоимость инвестиций. Сколько на данный момент стоимость актива.
    private BigDecimal lastInvestmentCost;
    // Прибыль
    private BigDecimal profit;
    // На сколько процентов изменился профит
    private BigDecimal profitPercent;
    private MathService service = new MathService();
    private HashMap<String, BigDecimal> coinHashMap;

    public Coin(String symbol, BigDecimal userBuyPrice, BigDecimal userCoinVolume) {
        this.symbol = symbol;
        this.userBuyPrice = userBuyPrice;
        this.userCoinVolume = userCoinVolume;
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

    public BigDecimal getActualPrice() {
        actualPrice = service.getActualCoinPriceBySymbol(symbol);
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
        profit = service.getProfitNumber(lastInvestmentCost, userInvestment);
        return profit;
    }

    public BigDecimal getProfitPercent() {
        profitPercent = service.getProfitPercent(actualPrice,userBuyPrice);
        return profitPercent;
    }

    public void getCoinInfo (){
        coinHashMap = service.getCoinHashMap();

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
