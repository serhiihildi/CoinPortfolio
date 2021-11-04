package com.petProject.portfolio.model;

import com.petProject.portfolio.service.getCurrentCoinPrice;

import java.math.BigDecimal;
import java.util.HashMap;

public class CoinList {

    private HashMap<String, BigDecimal> coinList;
    private getCurrentCoinPrice getCurrentCoinPrice;

    public void setCoinList(HashMap<String, BigDecimal> coinList) {
        this.coinList = coinList;
    }

    public HashMap<String, BigDecimal> getCoinList() {
        return coinList = getCurrentCoinPrice.getCoinHashMap();
    }
}
