package com.portfolio.coinportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.coinportfolio.service.PortfolioService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import static com.portfolio.coinportfolio.model.Coin.ANSI_GREEN_BOLD;
import static com.portfolio.coinportfolio.model.Coin.ANSI_RED_BOLD;

@Data
public class Portfolio {
    static final Logger logger = LogManager.getLogger(Portfolio.class.getName());
    public static final String ANSI_GREEN = "\033[1;92m";
    public static final String ANSI_GREEN_BACKGROUND = "\033[0;102m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

    private String name;
    private ArrayList<Coin> coinList;

    @JsonIgnore
    private PortfolioService portfolioService;
    @JsonIgnore
    private Price price;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private Map<String, Quotes> priceMap;
    @JsonIgnore
    private BigDecimal userInvestmentNumber;
    @JsonIgnore
    private BigDecimal profileProfitNumber;
    @JsonIgnore
    private BigDecimal currentUserPortfolioInvestmentNumber;

    public Portfolio() {
        if (getPriceMap() == null) {
            setPrice(new Price());
            setPriceMap(getPrice().getPriceMap());
        }
        setCoinList(new ArrayList<>());
        setPortfolioService(new PortfolioService());
    }

    public void addCoinToPortfolio(String symbol, BigDecimal userByuPrice,
                                   BigDecimal userCoinVolume) {
        if (getPriceMap() != null) {
            if (getCoinList() == null) {
                setCoinList(new ArrayList<>());
            }
            getCoinList().add(getPortfolioService().addCoinToCustomPortfolio(symbol, userByuPrice, userCoinVolume, getPriceMap()));
        }
        setCoinList(getCoinList());
    }

    public void setGlobalInfo() {
        setUserInvestmentNumber(getPortfolioService().getUserInvestmentNumber(getCoinList()));
        setCurrentUserPortfolioInvestmentNumber(getPortfolioService().getCurrentUserPortfolioInvestmentNumber(getCoinList()));
        setProfileProfitNumber(getPortfolioService().getProfileProfit(getCoinList()));
    }

    public void getGlobalInfo() {
        try {
            setGlobalInfo();
        } catch (Exception e) {
            logger.error("Failed to get full portfolio information. Method error");
        }
        getEditedPortfolioName();
        logger.info("Начальная цена портфеля: $" + getUserInvestmentNumber() );
        logger.info("Актуальная стоимость портфеля: $" + getCurrentUserPortfolioInvestmentNumber());
        logger.info("Прибыль: " + checkProfitAndTakeTrueColor() + '$' + getProfileProfitNumber() + ANSI_RESET);
    }

    private String checkProfitAndTakeTrueColor() {
        if (getProfileProfitNumber().signum() < 0) {
            return ANSI_RED_BOLD;
        }
        return ANSI_GREEN_BOLD;
    }

    public void getAllCoinInfoAtPortfolio() {
        getEditedPortfolioName();
        getCoinList().forEach(Coin::getAllInfoAboutCoin);
    }

    public void getAllMinusCoinsAtPortfolio() {
        getEditedPortfolioName();
        getCoinList().forEach(Coin::getAllMinusCoinsAtPortfolio);
    }

    public void getCoinsThatGaveMoreThan10Dollars() {
        getEditedPortfolioName();
        getCoinList().forEach(Coin::getCoinsThatGaveMoreThan10Dollars);
    }

    public void getCoinsThatGaveMoreThan50ProfitPercents() {
        getEditedPortfolioName();
        getCoinList().forEach(Coin::getCoinsThatGaveMoreThan50ProfitPercents);
    }

    private void getEditedPortfolioName() {
        logger.info(PURPLE_BACKGROUND + BLACK_BOLD + "[" + getName() + "]" + ANSI_RESET);
    }

    public void getAllPlusCoinsAtPortfolio() {
        getEditedPortfolioName();
        getCoinList().forEach(Coin::getAllPlusCoinsAtPortfolio);
    }

    @Override
    public String toString() {
        return name + "\n" + coinList;
    }
}
