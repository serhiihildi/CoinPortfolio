package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Coin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceService {

    public static final String URL_BINANCE = "https://www.binance.com/api/v3/ticker/price";

    public Map<String, Coin> getAMapOfCurrentQuotes() {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Coin> map = null;
        try {
            List<Coin> coinList = mapper.readValue(
                    new URL(URL_BINANCE),
                    new TypeReference<List<Coin>>() {
                    });

            map = coinList.stream()
                    .collect(Collectors.toMap(Coin::getSymbol, Function.identity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}




