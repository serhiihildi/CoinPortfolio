package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.model.Quotes;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface QuotesService {
    Map<String, Quotes> getAMapOfCurrentQuotes();
}
