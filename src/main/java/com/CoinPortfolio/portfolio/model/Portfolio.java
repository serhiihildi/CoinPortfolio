package com.CoinPortfolio.portfolio.model;

import java.util.ArrayList;

public class Portfolio {

    private ArrayList<Coin> coinArrayList;

    public void setCoinArrayList(ArrayList<Coin> coinArrayList) {
        this.coinArrayList = coinArrayList;
    }

    public ArrayList<Coin> getCoinArrayList() {
        return coinArrayList;
    }
}
