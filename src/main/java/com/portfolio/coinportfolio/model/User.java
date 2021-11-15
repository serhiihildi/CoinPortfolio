package com.portfolio.coinportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String password;
    private ArrayList<Portfolio> userPortfolioList;
    private BigDecimal userProfit;
    private BigDecimal currentUserPortfolioInvestmentNumber;
}