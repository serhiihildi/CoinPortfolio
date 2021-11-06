package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.GetCurrentCoinPrice;

import java.math.BigDecimal;
import java.util.HashMap;

public class CoinList {

    private HashMap<String, BigDecimal> coinList;
    private GetCurrentCoinPrice getCurrentCoinPrice;

    public void setCoinList(HashMap<String, BigDecimal> coinList) {
        this.coinList = coinList;
    }

    public HashMap<String, BigDecimal> getCoinList() {
        return coinList = getCurrentCoinPrice.getCoinHashMap();
    }
}
