package com.CoinPortfolio.portfolio.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Coin {

    private String symbol;
    private BigDecimal price;

    public Coin() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin testPrice = (Coin) o;
        return Objects.equals(symbol, testPrice.symbol) &&
                Objects.equals(price, testPrice.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price);
    }

    @Override
    public String toString() {
        return symbol + ": " + price;
    }
}
