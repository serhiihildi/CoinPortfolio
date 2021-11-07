package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.PriceService;

import java.math.BigDecimal;
import java.util.HashMap;

public class Price {
    private HashMap<String, BigDecimal> price;
    private PriceService service;

    public Price() {
        setService(new PriceService());
        setPrice(getService().getAMapOfCurrentQuotes());
    }

    public HashMap<String, BigDecimal> getPrice() {
        return price;
    }

    private void setPrice(HashMap<String, BigDecimal> price) {
        this.price = price;
    }

    private PriceService getService() {
        return service;
    }

    private void setService(PriceService service) {
        this.service = service;
    }
}
