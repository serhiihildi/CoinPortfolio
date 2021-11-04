package com.petProject.portfolio.сontrollers;

import com.petProject.portfolio.view.CoinView;
import com.petProject.portfolio.model.Coin;

public class CoinController {

    private Coin model;
    private CoinView view;

    public void setCoin(String coinName) {
        model.setCoinName(coinName);
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

//    public void updateView() {
//        view.printCoinDetails(model.getCoinName(), model.getCoinCurrentPrice());
//    }
}
