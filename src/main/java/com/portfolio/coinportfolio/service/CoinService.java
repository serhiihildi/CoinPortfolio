package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.impl.CoinServiceImpl;
import com.portfolio.coinportfolio.model.Coin;
import com.portfolio.coinportfolio.model.Quotes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CoinService implements CoinServiceImpl {

    static final Logger log = LogManager.getLogger(CoinService.class.getName());
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";
    public static final String GREEN_BACKGROUND = "\033[42m";
    public static final String ANSI_RED_BOLD = "\033[1;31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BOLD = "\033[1;30m";

    public void updateCoin(Coin coin, Map<String, Quotes> coinMap) {
        coin.setActualPrice(getActualCoinPriceBySymbol(coin.getSymbol(), coinMap));
        coin.setUserInvestment(getUserInvestmentNumber(coin.getUserCoinVolume(), coin.getUserBuyPrice()));
        coin.setLastInvestmentCost(getLastInvestmentCost(coin.getUserCoinVolume(), coin.getActualPrice()));
        coin.setProfit(getProfitNumber(coin.getLastInvestmentCost(), coin.getUserInvestment()));
        coin.setProfitPercent(getProfitPercent(coin.getActualPrice(), coin.getUserBuyPrice()));
    }

    public void getAllInfoAboutCoin(Coin coin) {
        log.info("Монета: " + GREEN_BACKGROUND + BLACK_BOLD + "[" + coin.getSymbol() + "]" + ANSI_RESET);
        log.info("Инвестиция: $" + coin.getUserInvestment() );
        log.info("Цена покупки: $" + coin.getUserBuyPrice());
        log.info("Актуальная стоимость: $" + coin.getActualPrice());
        log.info("Колическтво монет: " + coin.getUserCoinVolume());
        log.info("Актуальная стоимость актива: $" + coin.getLastInvestmentCost());
        log.info("Процент профита: " + getStringWithCurrentColorForPercent(coin.getProfitPercent()));
        log.info("Прибыль: " + getStringWithCurrentColorForMoney(coin.getProfitPercent())); //
        log.info("-------");
    }

    private String getStringWithCurrentColorForPercent(BigDecimal decimal) {
        if (decimal.signum() < 0) {
            return ANSI_RED_BOLD + decimal + '%' + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + decimal + '%' + ANSI_RESET;
    }

    private String getStringWithCurrentColorForMoney(BigDecimal decimal) {
        if (decimal.signum() < 0) {
            return ANSI_RED_BOLD + '$' + decimal + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + '$' + decimal + ANSI_RESET;
    }

    public void getAllMinusCoinsAtPortfolio(Coin coin) {
        if (coin.getProfit().signum() < 0) {
            getAllInfoAboutCoin(coin);
        }
    }

    public void getAllPlusCoinsAtPortfolio(Coin coin) {
        if (coin.getProfit().signum() >= 0) {
            getAllInfoAboutCoin(coin);
        }
    }

    public void getCoinsThatGaveMoreThan10Dollars(Coin coin) {
        if (coin.getProfit().compareTo(BigDecimal.TEN) > 0) {
            getAllInfoAboutCoin(coin);
        }
    }

    public void getCoinsThatGaveMoreThan50ProfitPercents(Coin coin) {
        if (coin.getProfitPercent().compareTo(new BigDecimal("50")) > 0) {
            getAllInfoAboutCoin(coin);
        }
    }

    @Override
    public BigDecimal getProfitPercent(BigDecimal actualPrice, BigDecimal userBuyPrice) {
            return ((actualPrice.subtract(userBuyPrice)).divide(userBuyPrice, 2, 2))
                    .multiply(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal getProfitNumber(BigDecimal lastInvestmentCost, BigDecimal userInvestment) {
        return lastInvestmentCost.subtract(userInvestment);
    }

    @Override
    public BigDecimal getLastInvestmentCost(BigDecimal userCoinVolume, BigDecimal actualPrice) {
        return userCoinVolume.multiply(actualPrice);
    }

    @Override
    public BigDecimal getUserInvestmentNumber(BigDecimal userCoinVolume, BigDecimal userBuyPrice) {
        return userBuyPrice.multiply(userCoinVolume);
    }

    @Override
    public BigDecimal getActualCoinPriceBySymbol(String symbol, Map<String, Quotes> coinHashMap) {
        if (coinHashMap.isEmpty()) {
            log.error("Карта актуальных котировок пуста.");
        }
        return coinHashMap.entrySet().stream()
                .filter(x -> symbol.equals(x.getKey()))
                .findFirst()
                .orElse(null)
                .getValue()
                .getPrice();
    }
}
