package com.petProject.portfolio.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Portfolio {

    private ArrayList<Coin> userPortfolio;

    public static void main(String[] args) {
        new Portfolio().doThis();
    }

    private void doThis() {

        userPortfolio = new ArrayList<>();
        userPortfolio.add(
                new Coin(
                        "ADAUSDT",
                        new BigDecimal("2.300333333"),
                        new BigDecimal("30.22200904")
                )
        );

        userPortfolio.add(
                new Coin(
                        "BTCUSDT",
                        new BigDecimal("48268.405"),
                        new BigDecimal("0.00303916")
                )
        );

        for (int i = 0; i < userPortfolio.size(); i++) {
            userPortfolio.get(i).getCoinInfo();
        }

    }

}
