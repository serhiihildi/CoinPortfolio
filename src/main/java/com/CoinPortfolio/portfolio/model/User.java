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

    public User() {
        setName("Новый юзер");
        setService(new UserService());
        setPortfolio(new Portfolio());
        setUserPortfolioList(new ArrayList<>());
    }

    public void takeTotalUserProfit()  {
        BigDecimal userProfitSum = getService().getUserProfitSum(getUserPortfolioList());
        setUserProfit(userProfitSum);
        logger.info("Общая прибыль пользователя: $" + getUserProfit());
    }

    public Portfolio createNewUserPortfolio(String name) {
        if (name.isEmpty()) {
            logger.error("Failed to create a new portfolio. The passed string is empty. " + getClass());
        }
        if (service == null) {
            service = new UserService();
        }
        return service.createNewUserPortfolio(name);
    }
}