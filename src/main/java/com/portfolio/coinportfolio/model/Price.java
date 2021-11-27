package com.portfolio.coinportfolio.model;

import com.portfolio.coinportfolio.service.impl.QuotesServiceImpl;
import lombok.Data;

import java.util.Map;

@Data
public class Price {
    private Map<String, Quotes> priceMap;
    private String symbol;
    private String price;

    public Price() {
        QuotesServiceImpl service = new QuotesServiceImpl();
        setPriceMap(service.getAMapOfCurrentQuotes());
    }
}
