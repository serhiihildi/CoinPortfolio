package com.portfolio.coinportfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Portfolio {

    private String name;
    private ArrayList<Coin> coinList;
    private BigDecimal userInvestmentNumber;
    private BigDecimal profileProfitNumber;
    private BigDecimal currentUserPortfolioInvestmentNumber;

    public Portfolio(String name, ArrayList<Coin> coinList) {
        this.name = name;
        this.coinList = coinList;
    }
}
