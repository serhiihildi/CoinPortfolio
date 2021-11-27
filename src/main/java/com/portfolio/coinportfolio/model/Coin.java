package com.portfolio.coinportfolio.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "coins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "symbol")
    @NonNull private String symbol;
    @Column(name = "buyPrice")
    @NonNull private BigDecimal userBuyPrice;
    @Column(name = "CoinVolume")
    @NonNull private BigDecimal userCoinVolume;
    @Column(name = "actualPrice")
    private BigDecimal actualPrice;
    @Column(name = "investment")
    private BigDecimal userInvestment;
    @Column(name = "lastInvestment")
    private BigDecimal lastInvestmentCost;
    @Column(name = "profitPercent")
    private int profitPercent;
    @Column(name = "profit")
    private BigDecimal profit;
}
