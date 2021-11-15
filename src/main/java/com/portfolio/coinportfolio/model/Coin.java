package com.portfolio.coinportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coin {
    @NonNull
    private String symbol;
    @NonNull
    private BigDecimal userBuyPrice;
    @NonNull
    private BigDecimal userCoinVolume;
    private BigDecimal actualPrice;
    private BigDecimal userInvestment;
    private BigDecimal lastInvestmentCost;
    private BigDecimal profitPercent;
    private BigDecimal profit;
}
