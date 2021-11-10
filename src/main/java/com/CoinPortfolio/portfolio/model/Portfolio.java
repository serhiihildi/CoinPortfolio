package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.PortfolioService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@Data
public class Portfolio {
    static final Logger logger = LogManager.getLogger(Portfolio.class.getName());

    private Map<String, Quotes> priceMap;
    private PortfolioService portfolioService;
    private ArrayList<UserCoin> userCoinList;
    private Price price;
    private User user;

    private String name;
    private BigDecimal userInvestmentNumber;
    private BigDecimal profileProfitNumber;
    private BigDecimal currentUserPortfolioInvestmentNumber;

    public Portfolio() {
        if (getPriceMap() == null) {
            setPrice(new Price());
            setPriceMap(getPrice().getPriceMap());
        }
        setUserCoinList(new ArrayList<>());
        setPortfolioService(new PortfolioService());
    }

    public void addCoinToPortfolio(String symbol, BigDecimal userByuPrice,
                                   BigDecimal userCoinVolume) {
        if (getPriceMap() != null) {
            if (getUserCoinList() == null) {
                setUserCoinList(new ArrayList<>());
            }
            getUserCoinList().add(getPortfolioService().addCoinToCustomPortfolio(symbol, userByuPrice, userCoinVolume, getPriceMap()));
        }
        setUserCoinList(getUserCoinList());
    }

    public void setGlobalInfo() {
        setUserInvestmentNumber(getPortfolioService().getUserInvestmentNumber(getUserCoinList()));
        setCurrentUserPortfolioInvestmentNumber(getPortfolioService().getCurrentUserPortfolioInvestmentNumber(getUserCoinList()));
        setProfileProfitNumber(getPortfolioService().getProfileProfit(getUserCoinList()));
    }

    public void getGlobalInfo() {
        try {
            setGlobalInfo();
        } catch (Exception e) {
            logger.error("Failed to get full portfolio information. Method error: " + e.getClass());
        }
        logger.info("Начальная цена портфеля: $" + getUserInvestmentNumber());
        logger.info("Актуальная стоимость портфеля: $" + getCurrentUserPortfolioInvestmentNumber());
        logger.info("Прибыль: $" + getProfileProfitNumber());
        logger.info("____________________");
    }

    @Override
    public String toString() {
        return "Биржа: " + getName() + "\n" + userCoinList;
    }

}