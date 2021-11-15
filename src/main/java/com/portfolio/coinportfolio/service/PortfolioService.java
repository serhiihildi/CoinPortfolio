package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.impl.PortfolioServiceImpl;
import com.portfolio.coinportfolio.model.Coin;
import com.portfolio.coinportfolio.model.Portfolio;
import com.portfolio.coinportfolio.model.Price;
import com.portfolio.coinportfolio.model.Quotes;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

import static com.portfolio.coinportfolio.service.CoinService.ANSI_GREEN_BOLD;
import static com.portfolio.coinportfolio.service.CoinService.ANSI_RED_BOLD;

public class PortfolioService implements PortfolioServiceImpl {

    static final Logger log = LogManager.getLogger(PortfolioService.class.getName());

    @Getter @Setter
    private Map<String, Quotes> priceMap;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

    @Override
    public Map<String, Quotes> getActualQuotes() {
        Price price = new Price();
        if (getPriceMap() == null) {
            setPriceMap(price.getPriceMap());
        }
        return price.getPriceMap();
    }

    @Override
    public void setGlobalInfo(Portfolio portfolio) {
        portfolio.setUserInvestmentNumber(getUserInvestmentNumber(portfolio.getCoinList()));
        portfolio.setCurrentUserPortfolioInvestmentNumber(getCurrentUserPortfolioInvestmentNumber(portfolio.getCoinList()));
        portfolio.setProfileProfitNumber(getProfileProfit(portfolio.getCoinList()));
    }

    public void updateCoinList(Portfolio pfList) {
        CoinService service = new CoinService();
        for (Coin coin : pfList.getCoinList()) {
            service.updateCoin(
                    coin,
                    getActualQuotes()
            );
        }
    }

    @Override
    public void getGlobalInfo(String name, Portfolio portfolio) {
        try {
            setGlobalInfo(portfolio);
        } catch (Exception e) {
            log.error("Failed to get full portfolio information. Method error");
        }
        log.info(getEditedPortfolioName(name));
        log.info("Начальная цена портфеля: $" + portfolio.getUserInvestmentNumber());
        log.info("Актуальная стоимость портфеля: $" + portfolio.getCurrentUserPortfolioInvestmentNumber());
        log.info("Прибыль: " + checkProfitAndTakeTrueColor(portfolio.getProfileProfitNumber()));
    }

    private String checkProfitAndTakeTrueColor(BigDecimal profileProfitNumber) {
        if (profileProfitNumber.signum() < 0) {
            return ANSI_RED_BOLD + '$' + profileProfitNumber + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + '$' + profileProfitNumber + ANSI_RESET;
    }

    @Override
    public void getAllCoinInfoAtPortfolio(String name, Portfolio portfolio) {
        log.info(getEditedPortfolioName(name));
        for (Coin coin : portfolio.getCoinList()) {
            CoinService service = new CoinService();
            service.getAllInfoAboutCoin(coin);
        }
    }

    public String getEditedPortfolioName(String name) {
        return PURPLE_BACKGROUND + BLACK_BOLD + "[" + name + "]" + ANSI_RESET;
    }

    @Override
    public void getAllMinusCoinsAtPortfolio(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinService service = new CoinService();
            service.getAllMinusCoinsAtPortfolio(coin);
        }
    }

    @Override
    public void getAllPlusCoinsAtPortfolio(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinService service = new CoinService();
            service.getAllPlusCoinsAtPortfolio(coin);
        }
    }

    @Override
    public void getCoinsThatGaveMoreThan10Dollars(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinService service = new CoinService();
            service.getCoinsThatGaveMoreThan10Dollars(coin);
        }
    }

    @Override
    public void getCoinsThatGaveMoreThan50ProfitPercents(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinService service = new CoinService();
            service.getCoinsThatGaveMoreThan50ProfitPercents(coin);
        }
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the total return on the portfolio
     */
    @Override
    public BigDecimal getProfileProfit(ArrayList<Coin> coinArrayList) {
        BigDecimal profitSum = BigDecimal.ZERO;
        checkCoinArrayList(coinArrayList);

        for (Coin value : coinArrayList) {
            BigDecimal profit = value.getProfit();
            profitSum = profit.add(profitSum).plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return profitSum;
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the value of the initial investment in the portfolio
     */
    @Override
    public BigDecimal getUserInvestmentNumber(ArrayList<Coin> coinArrayList) {
        checkCoinArrayList(coinArrayList);
        BigDecimal userInvestmentSum = BigDecimal.ZERO;
        for (Coin value : coinArrayList) {
            BigDecimal userInvestment = value.getUserInvestment();
            userInvestmentSum = userInvestment.add(userInvestmentSum).plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return userInvestmentSum;
    }

    private void checkCoinArrayList(ArrayList<Coin> coinArrayList) {
        if (coinArrayList.isEmpty()) {
            log.error("Failed to get (as a passed parameter) the list of available coins from the user.");
        }
    }

    /**
     *
     * @param coinArrayList
     * @return BigDecimal
     * Logic allowing the user to get the actual value of the portfolio
     */
    @Override
    public BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Coin> coinArrayList) {
        checkCoinArrayList(coinArrayList);
        BigDecimal sumOfLastInvestmentCost = BigDecimal.ZERO;
        for (Coin value : coinArrayList) {
            BigDecimal lastInvestmentCost = value.getLastInvestmentCost();
            sumOfLastInvestmentCost = lastInvestmentCost
                    .add(sumOfLastInvestmentCost)
                    .plus(new MathContext(4,RoundingMode.HALF_UP));
        }
        return sumOfLastInvestmentCost;
    }
}
