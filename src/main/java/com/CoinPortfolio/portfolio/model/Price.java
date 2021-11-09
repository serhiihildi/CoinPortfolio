package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.PriceService;

import java.util.Map;

public class Price {
    private Map<String, Coin> priceMap;
    private PriceService service;
    private String symbol;
    private String price;

    public Price() {
        setService(new PriceService());
        setPriceMap(getService().getAMapOfCurrentQuotes());
    }

    public Map<String, Coin> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Coin> priceMap) {
        this.priceMap = priceMap;
    }

    private PriceService getService() {
        return service;
    }

    private void setService(PriceService service) {
        this.service = service;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
