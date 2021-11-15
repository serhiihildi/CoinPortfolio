package com.portfolio.coinportfolio.model;

import com.portfolio.coinportfolio.service.QuotesService;

import java.util.Map;

public class Price {
    private Map<String, Quotes> priceMap;
    private QuotesService service;
    private String symbol;
    private String price;

    public Price() {
        setService(new QuotesService());
        setPriceMap(getService().getAMapOfCurrentQuotes());
    }

    public Map<String, Quotes> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Quotes> priceMap) {
        this.priceMap = priceMap;
    }

    private QuotesService getService() {
        return service;
    }

    private void setService(QuotesService service) {
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
