package com.CoinPortfolio.portfolio.сontrollers;

import com.CoinPortfolio.portfolio.view.CoinView;
import com.CoinPortfolio.portfolio.model.Coin;

public class CoinController {

    private Coin model;
    private CoinView view;

    public void setCoin(String coinName) {
//        model.setSymbol(coinName);
    }

    public Coin getModel() {
        return model;
    }

    public CoinController() {
    }

    public CoinController(Coin model, CoinView view) {
        this.model = model;
        this.view = view;
    }
}
