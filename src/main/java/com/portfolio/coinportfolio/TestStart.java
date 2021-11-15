package com.portfolio.coinportfolio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.coinportfolio.model.Portfolio;
import com.portfolio.coinportfolio.model.User;
import com.portfolio.coinportfolio.service.PortfolioService;
import com.portfolio.coinportfolio.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestStart {
    static final Logger logger = LogManager.getLogger(TestStart.class.getName());
    private User user;

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        new TestStart().start();
                        Thread.sleep(900000);
                    } catch (InterruptedException ex) {
                        logger.error("Error creating a new thread and starting the test application");
                    }
                }
            }
        });
        t.start();
    }

    private void start() {
        user = new User();
        try {
            addPortfolio();
        } catch (Exception e) {
            logger.error("Failed to create new user portfolio and add coins");
        }

        for (Portfolio portfolio : user.getUserPortfolioList()) {
            PortfolioService service = new PortfolioService();
            String name = portfolio.getName();

            // Показать все монеты
            service.getAllCoinInfoAtPortfolio(name, portfolio);

            // Показать все минусовые монеты
//            service.getAllMinusCoinsAtPortfolio(name, portfolio);

            // Показать все плюсовые монеты
//            service.getAllPlusCoinsAtPortfolio(name, portfolio);

            // Показать монеты, которые дали больше 10$
//            service.getCoinsThatGaveMoreThan10Dollars(name, portfolio);

            // Показать монеты, которые дали больше 50%
//            service.getCoinsThatGaveMoreThan50ProfitPercents(name, portfolio);
        }

        for (Portfolio portfolio : user.getUserPortfolioList()) {
            PortfolioService service = new PortfolioService();
            String name = portfolio.getName();
            // Показать всю глобальную информацию о портфеле
            service.getGlobalInfo(name, portfolio);
        }

        try {
            // Показать общую прибыль пользователя
            UserService userService = new UserService();
            userService.takeTotalUserProfit(user.getUserPortfolioList());
        } catch (Exception e) {
            logger.error("Failed to get \"Total User Profit\"");
        }
    }

    private void addPortfolio() {
        if (user.getUserPortfolioList() == null) {
            user.setUserPortfolioList(new ArrayList<>());
        }
        user.getUserPortfolioList().add(takeJsonWithCoin("Binance.json"));
        user.getUserPortfolioList().add(takeJsonWithCoin("Okex.json"));

        for (Portfolio pfList : user.getUserPortfolioList()) {
            new PortfolioService().updateCoinList(pfList);
        }
    }

    private Portfolio takeJsonWithCoin(String json) {
        ObjectMapper mapper = new ObjectMapper();
        File src = new File(json);
        Portfolio portfolio = null;
        try {
            portfolio = mapper.readValue(src, new TypeReference<Portfolio>() {
            });
        } catch (IOException e) {
            logger.error("Не удалось прочитать JSON монет");
        }
        return portfolio;
    }
}

