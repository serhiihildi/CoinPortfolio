package com.portfolio.coinportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.coinportfolio.service.CoinService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Coin {

    private String symbol;
    private BigDecimal actualPrice;
    private BigDecimal userBuyPrice;
    private BigDecimal userCoinVolume;
    private BigDecimal userInvestment;
    private BigDecimal lastInvestmentCost;
    private BigDecimal profitPercent;
    private BigDecimal profit;
    @JsonIgnore
    private CoinService service;
    @JsonIgnore
    private Map<String, Quotes> coinMapWithActualPrice;

    static final Logger logger = LogManager.getLogger(Coin.class.getName());
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String ANSI_RED_BOLD = "\033[1;31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BOLD = "\033[1;30m";

    public Coin(String symbol, BigDecimal userBuyPrice, BigDecimal userCoinVolume, Map<String, Quotes> coinMap) {
        setService(new CoinService());
        setCoinMapWithActualPrice(coinMap);
        setSymbol(symbol);
        setUserBuyPrice(userBuyPrice);
        setUserCoinVolume(userCoinVolume);
        setActualPrice(getService().getActualCoinPriceBySymbol(getSymbol(), getCoinMapWithActualPrice()));
        setUserInvestment(getService().getUserInvestmentNumber(getUserCoinVolume(), getUserBuyPrice()));
        setLastInvestmentCost(getService().getLastInvestmentCost(getUserCoinVolume(), getActualPrice()));
        setProfit(getService().getProfitNumber(getLastInvestmentCost(), getUserInvestment()));
        setProfitPercent(getService().getProfitPercent(getActualPrice(), getUserBuyPrice()));
    }

    public void getAllInfoAboutCoin() {
        logger.info("Монета: " + GREEN_BACKGROUND + BLACK_BOLD + "[" + getSymbol() + "]" + ANSI_RESET);
        logger.info("Инвестиция: $" + getUserInvestment() );
        logger.info("Цена покупки: $" + getUserBuyPrice());
        logger.info("Актуальная стоимость: $" + getActualPrice());
        logger.info("Колическтво монет: " + getUserCoinVolume());
        logger.info("Актуальная стоимость актива: $" + getLastInvestmentCost());
        logger.info("Процент профита: " + checkDecimalNumberAndTakeTrueColour(getProfitPercent()) + getProfitPercent() + "%" + ANSI_RESET);
        logger.info("Прибыль: " + checkDecimalNumberAndTakeTrueColour(getProfit()) + '$' + getProfit() + ANSI_RESET);
        logger.info("-------");
    }

    private String checkDecimalNumberAndTakeTrueColour(BigDecimal decimal) {
        if (decimal.signum() < 0) {
            return ANSI_RED_BOLD;
        }
        return ANSI_GREEN_BOLD;
    }

    public void getAllMinusCoinsAtPortfolio() {
        if (getProfit().signum() < 0) {
            getAllInfoAboutCoin();
        }
    }

    public void getAllPlusCoinsAtPortfolio() {
        if (getProfit().signum() >= 0) {
            getAllInfoAboutCoin();
        }
    }

    public void getCoinsThatGaveMoreThan10Dollars() {
        if (getProfit().compareTo(BigDecimal.TEN) > 0) {
            getAllInfoAboutCoin();
        }
    }

    public void getCoinsThatGaveMoreThan50ProfitPercents() {
        if (getProfitPercent().compareTo(new BigDecimal("50")) > 0) {
            getAllInfoAboutCoin();
        }
    }
}
