package com.petProject.portfolio.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class getCurrentCoinPrice {

    public static final String URL = "https://www.binance.com/api/v3/ticker/price";

    public HashMap<String, BigDecimal> getCoinHashMap() {
        return getMapWithCurrentCoinPriceJackson(getJsonWithCurrentPriceFromBinance());
    }

    // Jackson
    private HashMap<String, BigDecimal> getMapWithCurrentCoinPriceJackson(String coinList) {
        HashMap<String, BigDecimal> coinHashMap = new HashMap<>();
        String symbol = null;
        BigDecimal price;

        try {
            JsonParser jsonParser = new JsonFactory().createParser(coinList);
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

    // Apache HttpClient
    private String getJsonWithCurrentPriceFromBinance() {
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

    public static void main (String[] args) {
        HashMap<String, BigDecimal> coinHashMap = new getCurrentCoinPrice().getCoinHashMap();
        for (String key : coinHashMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + coinHashMap.get(key));
        }
    }

    // JSONParser
    private HashMap<String, BigDecimal> getMapWithCurrentCoinsPrice(String coinList) {
        HashMap<String, BigDecimal> coinHashMap = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray a = (JSONArray) parser.parse(coinList);

            for (Object o : a) {
                JSONObject coin = (JSONObject) o;

                String symbol = (String) coin.get("symbol");
                BigDecimal price = new BigDecimal((String) coin.get("price"));
                coinHashMap.put(symbol, price);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return coinHashMap;
    }

    // HttpURLConnection
    private String getUnparsedListCurrentCoinPrice() {

        HttpURLConnection connection;
        BufferedReader br = null;
        String coinList = null;

        try {
            connection = (HttpURLConnection) new URL(URL).openConnection();
            if (connection != null) {
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(200);
                connection.setReadTimeout(4000);
                connection.connect();
                if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    br = new BufferedReader(isr);
                }
                coinList = br != null ? br.readLine() : null;
            }
        } catch (IOException e) {
            //TODO Realise Exception message;
            e.printStackTrace();
        }
        return coinList;
    }
}




