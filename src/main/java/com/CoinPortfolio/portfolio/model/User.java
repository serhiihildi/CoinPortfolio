package com.CoinPortfolio.portfolio.model;

import com.CoinPortfolio.portfolio.service.UserService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class User {

    private UserService service;
    private Portfolio portfolio;
    private ArrayList<Portfolio> userPortfolioList;
    private String name;
    private String password;
    private BigDecimal userProfit;

    static final Logger logger = LogManager.getLogger(User.class.getName());
    public static final String ANSI_GREEN_BACKGROUND = "\033[1;92m";
    public static final String ANSI_RESET = "\u001B[0m";

    public User() {
        setName("Новый юзер");
        setService(new UserService());
        setPortfolio(new Portfolio());
        setUserPortfolioList(new ArrayList<>());
    }

    public void takeTotalUserProfit()  {
        BigDecimal userProfitSum = getService().getUserProfitSum(getUserPortfolioList());
        setUserProfit(userProfitSum);
        logger.info("---------------------------------------");
        logger.info("Общая прибыль пользователя: " + ANSI_GREEN_BACKGROUND + "$"  + getUserProfit() + ANSI_RESET);
        logger.info("---------------------------------------");
    }

    public Portfolio createNewUserPortfolio(String name) {
        if (name.isEmpty()) {
            logger.error("Failed to create a new portfolio. The passed string is empty.");
        }
        if (service == null) {
            service = new UserService();
        }
        return service.createNewUserPortfolio(name);
    }
}