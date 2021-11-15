package com.portfolio.coinportfolio.service;

import com.portfolio.coinportfolio.impl.UserServiceImpl;
import com.portfolio.coinportfolio.model.Portfolio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.portfolio.coinportfolio.service.CoinService.ANSI_GREEN_BOLD;
import static com.portfolio.coinportfolio.service.CoinService.ANSI_RED_BOLD;
import static com.portfolio.coinportfolio.service.PortfolioService.ANSI_RESET;

public class UserService implements UserServiceImpl {
    static final Logger log = LogManager.getLogger(UserService.class.getName());

    @Override
    public BigDecimal getUserProfitSum(ArrayList<Portfolio> userPortfolio)  {
        BigDecimal profileProfitNumber = BigDecimal.ZERO;
        for (Portfolio value : userPortfolio) {
            profileProfitNumber = profileProfitNumber.add(value.getProfileProfitNumber());
        }
        return profileProfitNumber;
    }

    public BigDecimal getCurrentUserPortfolioInvestmentNumber(ArrayList<Portfolio> userPortfolio) {
        BigDecimal profileInvestmentNumber = BigDecimal.ZERO;
        for (Portfolio value : userPortfolio) {
            profileInvestmentNumber = profileInvestmentNumber.add(value.getCurrentUserPortfolioInvestmentNumber());
        }
        return profileInvestmentNumber;
    }

    public void takeTotalUserProfit(ArrayList<Portfolio> userPortfolio)  {
        BigDecimal currentUserPortfolioInvestmentNumber = getCurrentUserPortfolioInvestmentNumber(userPortfolio);
        BigDecimal userProfitSum = getUserProfitSum(userPortfolio);
        log.info("---------------------------------------");
        log.info("Текущая сумма на счетах (с учетом прибыли): " + checkProfitAndTakeTrueColor(currentUserPortfolioInvestmentNumber));
        log.info("Общая прибыль пользователя: " + checkProfitAndTakeTrueColor(userProfitSum));
        log.info("---------------------------------------");
    }

    private String checkProfitAndTakeTrueColor(BigDecimal number) {
        if (number.signum() < 0) {
            return ANSI_RED_BOLD + '$' + number + ANSI_RESET;
        }
        return ANSI_GREEN_BOLD + '$' + number + ANSI_RESET;
    }
}
