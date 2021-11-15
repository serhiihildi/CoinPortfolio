package com.portfolio.coinportfolio.impl;

import com.portfolio.coinportfolio.model.Quotes;

import java.util.Map;

public interface QuotesServiceImpl {
    Map<String, Quotes> getAMapOfCurrentQuotes();
}
