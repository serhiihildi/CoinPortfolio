package com.petProject.portfolio.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Coin {

    private String coinName;
    private HashMap<String, BigDecimal> buyPrice;
    private BigDecimal coinVolume;
    private BigDecimal coinProfit; // Профит по монете
    private BigDecimal actualVolume;
    private BigDecimal actualPrice; // Take at method getCurrentCoinPrice();


    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public HashMap<String, BigDecimal> getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(HashMap<String, BigDecimal> buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getCoinVolume() {
        return coinVolume;
    }

    public void setCoinVolume(BigDecimal coinVolume) {
        this.coinVolume = coinVolume;
    }

    public BigDecimal getCoinProfit() {
        return coinProfit;
    }

    public void setCoinProfit(BigDecimal coinProfit) {
        this.coinProfit = coinProfit;
    }

    public BigDecimal getActualVolume() {
        return actualVolume;
    }

    public void setActualVolume(BigDecimal actualVolume) {
        this.actualVolume = actualVolume;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
}
