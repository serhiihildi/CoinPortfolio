package com.portfolio.coinportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Table(name = "portfolio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "name")
    private String name;
    @Column(name = "coinList")
    private ArrayList<Coin> coinList;
    @Column(name = "userInvestmentNumber")
    private BigDecimal userInvestmentNumber;
    @Column(name = "profileProfitNumber")
    private BigDecimal profileProfitNumber;
    @Column(name = "currentUserPortfolioInvestmentNumber")
    private BigDecimal currentUserPortfolioInvestmentNumber;
    @Column(name = "profitPercent")
    private int profitPercent;

    public Portfolio(String name, ArrayList<Coin> coinList) {
        this.name = name;
        this.coinList = coinList;
    }
}
