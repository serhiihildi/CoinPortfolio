package com.CoinPortfolio.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Quotes {
    @NonNull
    private String symbol;
    @NonNull
    private BigDecimal price;
}
