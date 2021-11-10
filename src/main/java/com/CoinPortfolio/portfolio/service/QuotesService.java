package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.impl.QuotesServiceImpl;
import com.CoinPortfolio.portfolio.model.Quotes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuotesService implements QuotesServiceImpl {
    static final Logger logger = LogManager.getLogger(QuotesService.class.getName());

    public static final String URL_BINANCE = "https://www.binance.com/api/v3/ticker/price";

    public Map<String, Quotes> getAMapOfCurrentQuotes() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Quotes> map = null;

        URL src = null;
        try {
            src = new URL(URL_BINANCE);
        } catch (MalformedURLException e) {
            logger.error("Failed to connect to the site: " + URL_BINANCE);
        }

        try {
            List<Quotes> quotesList = mapper.readValue(
                    src,
                    new TypeReference<List<Quotes>>() {
                    });
            if (quotesList.isEmpty()){
                logger.error("Jackson was unable to retrieve data from the provided JSON " + quotesList.getClass());
            }
            map = quotesList.stream().collect(Collectors.toMap(Quotes::getSymbol, Function.identity()));
        } catch (IOException e) {
            logger.error("Failed to get a map of current quotes.");
        }
        return map;
    }
}




