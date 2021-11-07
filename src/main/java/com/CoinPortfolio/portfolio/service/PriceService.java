package com.CoinPortfolio.portfolio.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class PriceService {

    public static final String URL = "https://www.binance.com/api/v3/ticker/price";

    public HashMap<String, BigDecimal> getAMapOfCurrentQuotes() {
        return getAMapOfCurrentQuotesJackson(getJsonWithTheCurrentQuotesOfEheBinanceExchange());
    }

    /**
     * Jackson
     * @param coinList
     * @return HashMap<String, BigDecimal>
     * Метод парсит полученную строку содержащую актуальные котировками валютных пар биржы Binance
     */
    private HashMap<String, BigDecimal> getAMapOfCurrentQuotesJackson(String coinList) {
        HashMap<String, BigDecimal> coinHashMap = new HashMap<>();
        String symbol = null;
        BigDecimal price;

        try (JsonParser jsonParser = new JsonFactory().createParser(coinList)){
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = jsonParser.getCurrentName();
                    if ("symbol".equals(fieldName)) {
                        jsonParser.nextToken();
                        symbol = jsonParser.getText();
                    }
                    if ("price".equals(fieldName)) {
                        jsonParser.nextToken();
                        price = new BigDecimal(jsonParser.getText());
                        coinHashMap.put(symbol, price);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coinHashMap;
    }

    /**
     * Apache HttpClient
     * @return String
     * Получение актуальных котировок валютных пар от биржы Binance
     */
    private String getJsonWithTheCurrentQuotesOfEheBinanceExchange() {
        String responseBody = null;
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(URL);
            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final ClassicHttpResponse response) throws IOException, org.apache.hc.core5.http.ParseException {
                    final int status = response.getCode();
                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                        final HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            responseBody = httpclient.execute(httpget, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}




