package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.CoinService;

import java.math.BigDecimal;
import java.util.Map;

public class UserCoin {

    private CoinService service;
    private Map<String, Coin> coinMapWithActualPrice;
    private String symbol;
    private BigDecimal actualPrice;
    private BigDecimal userBuyPrice;
    private BigDecimal userCoinVolume;
    private BigDecimal userInvestment;
    private BigDecimal lastInvestmentCost;
    private BigDecimal profitPercent;
    private BigDecimal profit;

    public UserCoin(String symbol, BigDecimal userBuyPrice, BigDecimal userCoinVolume, Map<String, Coin> coinMap) {
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

    public CoinService getService() {
        return service;
    }

    public void setService(CoinService service) {
        this.service = service;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setUserInvestment(BigDecimal userInvestment) {
        this.userInvestment = userInvestment;
    }

    public void setLastInvestmentCost(BigDecimal lastInvestmentCost) {
        this.lastInvestmentCost = lastInvestmentCost;
    }

    private void setUserCoinVolume(BigDecimal userCoinVolume) {
        this.userCoinVolume = userCoinVolume;
    }

    private void setUserBuyPrice(BigDecimal userBuyPrice) {
        this.userBuyPrice = userBuyPrice;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setProfitPercent(BigDecimal profitPercent) {
        this.profitPercent = profitPercent;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public void setCoinMapWithActualPrice(Map<String, Coin> coinMapWithActualPrice) {
        this.coinMapWithActualPrice = coinMapWithActualPrice;
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
        return actualPrice;
    }

    public BigDecimal getUserInvestment() {
        return userInvestment;
    }

    public BigDecimal getLastInvestmentCost() {
        return lastInvestmentCost;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public BigDecimal getProfitPercent() {
        return profitPercent;
    }

    public Map<String, Coin> getCoinMapWithActualPrice() {
        return coinMapWithActualPrice;
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
