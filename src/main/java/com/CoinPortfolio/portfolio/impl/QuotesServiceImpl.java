package com.CoinPortfolio.portfolio.impl;

import com.CoinPortfolio.portfolio.model.Quotes;

import java.util.Map;

public interface QuotesServiceImpl {
    Map<String, Quotes> getAMapOfCurrentQuotes();
}
