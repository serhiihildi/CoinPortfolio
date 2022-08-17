package com.portfolio.coinportfolio.service.impl;

import com.portfolio.coinportfolio.model.Coin;
import com.portfolio.coinportfolio.model.Portfolio;
import com.portfolio.coinportfolio.model.Price;
import com.portfolio.coinportfolio.model.Quotes;
import com.portfolio.coinportfolio.service.PortfolioService;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

import static com.portfolio.coinportfolio.service.impl.CoinServiceImpl.ANSI_GREEN_BOLD;
import static com.portfolio.coinportfolio.service.impl.CoinServiceImpl.ANSI_RED_BOLD;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    static final Logger log = LogManager.getLogger(PortfolioServiceImpl.class.getName());

    @Getter
    @Setter
    private Map<String, Quotes> priceMap;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BACKGROUND = "\033[45m";
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";

    @Override
    public Map<String, Quotes> getActualQuotes() {
        if (getPriceMap() == null) {
            setPriceMap(new Price().getPriceMap());
        }
    return getPriceMap();
    }

    public void showBtcCost(String symbol, String symbol2, String symbol3) {
        Map<String, Quotes> getPriceMap = getActualQuotes();
        if (!getPriceMap.containsKey(symbol)){
            log.error("Failed to get cost by symbol. Something trouble with quotes map. " +
                    "Method: showBtcCost(String symbol)");
        }

        log.info("------------------------");
        String message = setText(symbol, getPriceMap) + ", " + setText(symbol2, getPriceMap)
                + ", " + setText(symbol3, getPriceMap);
        log.info(message);

    }

    private String setText(String symbol, Map<String, Quotes> getPriceMap) {
        return setUpperCaseAndSubstringForSymbol(symbol) + ": " + BLUE_BOLD_BRIGHT + "$"
                + setScaleForSymbols(symbol, getPriceMap) + ANSI_RESET;
    }

    private String setUpperCaseAndSubstringForSymbol(String symbol) {
        return symbol.toUpperCase().substring(0, 3);
    }

    private BigDecimal setScaleForSymbols(String symbol, Map<String, Quotes> getPriceMap) {
        return getPriceMap.get(symbol).getPrice().setScale(2);
    }

    @Override
    public void setGlobalInfo(Portfolio portfolio) {
        portfolio.setUserInvestmentNumber(getUserInvestmentNumber(portfolio.getCoinList()));
        portfolio.setCurrentUserPortfolioInvestmentNumber(getCurrentUserPortfolioInvestmentNumber(portfolio.getCoinList()));
        portfolio.setProfileProfitNumber(getProfileProfit(portfolio.getCoinList()));
        portfolio.setProfitPercent(getProfitPercent(portfolio.getUserInvestmentNumber(), portfolio.getCurrentUserPortfolioInvestmentNumber()));
    }

    private int getProfitPercent(BigDecimal userInvestmentNumber, BigDecimal currentUserPortfolioInvestmentNumber) {
        BigDecimal profitPercent = ((currentUserPortfolioInvestmentNumber.subtract(userInvestmentNumber))
                .divide(userInvestmentNumber, 2,2))
                .multiply(new BigDecimal(100));
        return profitPercent.intValue();
    }

    public void updateCoinList(Portfolio pfList) {
        CoinServiceImpl service = new CoinServiceImpl();
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
        String message = "Начальная цена портфеля: " + BLUE_BOLD_BRIGHT + "$" + portfolio.getUserInvestmentNumber() + ANSI_RESET;
        log.info(message);
        String message1 = "Актуальная стоимость портфеля: " + BLUE_BOLD_BRIGHT + "$" + portfolio.getCurrentUserPortfolioInvestmentNumber() + ANSI_RESET;
        log.info(message1);
        String message2 = "Процент прибыли портфеля: " + checkProfitAndTakeTrueColor(portfolio.getProfitPercent());
        log.info(message2);
        String message3 = "Прибыль: " + checkProfitAndTakeTrueColor(portfolio.getProfileProfitNumber());
        log.info(message3);
    }

    private String checkProfitAndTakeTrueColor(BigDecimal profileProfitNumber) {
        if (profileProfitNumber.signum() < 0) {
            return ANSI_RED_BOLD + '$' + profileProfitNumber + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + '$' + profileProfitNumber + ANSI_RESET;
    }

    private String checkProfitAndTakeTrueColor(int profileProfitNumber) {
        if (profileProfitNumber < 0) {
            return ANSI_RED_BOLD + profileProfitNumber + '%' + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + '+' + profileProfitNumber + '%' + ANSI_RESET;
    }

    @Override
    public void getAllCoinInfoAtPortfolio(String name, Portfolio portfolio) {
        log.info(getEditedPortfolioName(name));
        for (Coin coin : portfolio.getCoinList()) {
            CoinServiceImpl service = new CoinServiceImpl();
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
            CoinServiceImpl service = new CoinServiceImpl();
            service.getAllMinusCoinsAtPortfolio(coin);
        }
    }

    @Override
    public void getAllPlusCoinsAtPortfolio(String name, Portfolio portfolio) {
        log.info(getEditedPortfolioName(name));
        for (Coin coin : portfolio.getCoinList()) {
            CoinServiceImpl service = new CoinServiceImpl();
            service.getAllPlusCoinsAtPortfolio(coin);
        }
    }

    @Override
    public void getCoinsThatGaveMoreThan10Dollars(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinServiceImpl service = new CoinServiceImpl();
            service.getCoinsThatGaveMoreThan10Dollars(coin);
        }
    }

    @Override
    public void getCoinsThatGaveMoreThan500ProfitPercents(String name, Portfolio portfolio) {
        getEditedPortfolioName(name);
        for (Coin coin : portfolio.getCoinList()) {
            CoinServiceImpl service = new CoinServiceImpl();
            service.getCoinsThatGaveMoreThan50ProfitPercents(coin);
        }
    }

    /**
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
            userInvestmentSum = userInvestment.add(userInvestmentSum)
                    .plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return userInvestmentSum;
    }

    private void checkCoinArrayList(ArrayList<Coin> coinArrayList) {
        if (coinArrayList.isEmpty()) {
            log.error("Failed to get (as a passed parameter) the list of available coins from the user.");
        }
    }

    /**
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
                    .plus(new MathContext(4, RoundingMode.HALF_UP));
        }
        return sumOfLastInvestmentCost;
    }

    public void showCoinsThatGaveTheSpecifiedNumberOfProfit(int expectedProfitPercentage, Portfolio portfolio) {
        ArrayList<String> symbols = new ArrayList<>();
        int num = 0;

        for (Coin coin : portfolio.getCoinList()) {
            if (coin.getProfitPercent() >= expectedProfitPercentage) {
                num++;
                symbols.add(coin.getSymbol() + ": >$" + coin.getProfit().intValue());
            }
        }

        int size = portfolio.getCoinList().size();

        String message = getExpectedProfitPercentage(expectedProfitPercentage) + BLUE_BOLD_BRIGHT + num + "/" + size
                + " [" + showSymbols(symbols) + "] " + ANSI_RESET;
        if (num != 0) {
            log.info(message);
        }
    }

    private String getExpectedProfitPercentage(int expectedProfitPercentage) {
        if (expectedProfitPercentage < 100) {
            return ">" + expectedProfitPercentage + "%: ";
        } return "x" + ((expectedProfitPercentage / 100) + 1) + ": ";
    }

    private StringBuilder showSymbols(ArrayList<String> symbols) {
        StringBuilder temp = new StringBuilder();
        for (String symbol : symbols) {
            if (temp.length() <= 0) {
                temp.append(symbol);
            } else {
                temp.append(", ").append(symbol);
            }
        }
        return temp;
    }
}
