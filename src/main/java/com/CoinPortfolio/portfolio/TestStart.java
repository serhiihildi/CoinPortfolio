package com.CoinPortfolio.portfolio;

import com.CoinPortfolio.portfolio.model.User;

import java.math.BigDecimal;

public class TestStart {
    private User user;
    static int counter = 0;

    public static void main(String[] args) {

//        TimerTask timerTask = new TimerTask() {
//
//            @Override
//            public void run() {
//                counter++;
//            }
//        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        new TestStart().start();

//                        if (counter > 500) {
//                            timer.cancel();
//                            break;//end this loop
//                        }
                        Thread.sleep(60000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        t.start();

    }

    private void start() {
        user = new User();
        try {
            addFirstPortfolio();
            addSecondPortfolio();
        } catch (Exception e) {
            // TODO:
            e.printStackTrace();
        }

//        user.getUserPortfolioList().forEach(Portfolio::getGlobalInfo);

        try {
            user.takeUserProfit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        user.getUserPortfolioList().forEach(System.out::println);

    }

    private void addSecondPortfolio() throws Exception {
        user.setPortfolio(user.createNewUserPortfolio("Okex"));

        user.getPortfolio().addCoinToPortfolio(
                "SOLUSDT",
                new BigDecimal("142.17"),
                new BigDecimal("0.5272")
        );

        user.getPortfolio().addCoinToPortfolio(
                "ADAUSDT",
                new BigDecimal("2.13"),
                new BigDecimal("11.70999456")
        );

        user.getPortfolio().addCoinToPortfolio(
                "DOTUSDT",
                new BigDecimal("29.84"),
                new BigDecimal("0.837801")
        );

        user.getPortfolio().addCoinToPortfolio(
                "LUNAUSDT",
                new BigDecimal("28.78"),
                new BigDecimal("0.868688")
        );

        user.getPortfolio().addCoinToPortfolio(
                "XLMUSDT",
                new BigDecimal("0.29"),
                new BigDecimal("87.719298")
        );

        user.getPortfolio().addCoinToPortfolio(
                "XRPUSDT",
                new BigDecimal("0.955"),
                new BigDecimal("26.18856")
        );

        user.getPortfolio().addCoinToPortfolio(
                "BTCUSDT",
                new BigDecimal("42457.720"),
                new BigDecimal("0.00011521")
        );

        user.setUserPortfolioList(user.getService().addNewUserPortfolioToList(user.getPortfolio()));
    }

    private void addFirstPortfolio() throws Exception {
        user.setPortfolio(user.createNewUserPortfolio("Binance"));

        user.getPortfolio().addCoinToPortfolio(
                "BTCUSDT",
                new BigDecimal("48268.405"),
                new BigDecimal("0.00303916")
        );

        user.getPortfolio().addCoinToPortfolio(
                "ADAUSDT",
                new BigDecimal("2.300333333"),
                new BigDecimal("30.22200904")
        );

        user.getPortfolio().addCoinToPortfolio(
                "ETHUSDT",
                new BigDecimal("3747.48"),
                new BigDecimal("0.02400045")
        );

        user.getPortfolio().addCoinToPortfolio(
                "BNBUSDT",
                new BigDecimal("439.633"),
                new BigDecimal("0.19101295")
        );

        user.getPortfolio().addCoinToPortfolio(
                "DASHUSDT",
                new BigDecimal("209.525"),
                new BigDecimal("0.12000690")
        );

        user.getPortfolio().addCoinToPortfolio(
                "WAVESUSDT",
                new BigDecimal("27.790"),
                new BigDecimal("2.00997539")
        );

        user.getPortfolio().addCoinToPortfolio(
                "LTCUSDT",
                new BigDecimal("191.3"),
                new BigDecimal("0.26076419")
        );

        user.getPortfolio().addCoinToPortfolio(
                "THETAUSDT",
                new BigDecimal("6.9015"),
                new BigDecimal("3.30010344")
        );

        user.getPortfolio().addCoinToPortfolio(
                "DOTUSDT",
                new BigDecimal("31.090"),
                new BigDecimal("1.4567228")
        );

        user.getPortfolio().addCoinToPortfolio(
                "XRPUSDT",
                new BigDecimal("1.20295"),
                new BigDecimal("36.98182385")
        );

        user.getPortfolio().addCoinToPortfolio(
                "FLOWUSDT",
                new BigDecimal("22.860"),
                new BigDecimal("1.76823000")
        );

        user.getPortfolio().addCoinToPortfolio(
                "KAVAUSDT",
                new BigDecimal("7.1145"),
                new BigDecimal("5.60959568")
        );

        user.getPortfolio().addCoinToPortfolio(
                "NEOUSDT",
                new BigDecimal("53.780"),
                new BigDecimal("0.73926")
        );

        user.getPortfolio().addCoinToPortfolio(
                "SOLUSDT",
                new BigDecimal("144.883"),
                new BigDecimal("0.27222889")
        );

        user.getPortfolio().addCoinToPortfolio(
                "AXSUSDT",
                new BigDecimal("138.195"),
                new BigDecimal("0.22977")
        );

        user.getPortfolio().addCoinToPortfolio(
                "ATOMUSDT",
                new BigDecimal("24.145"),
                new BigDecimal("1.23874730")
        );

        user.getPortfolio().addCoinToPortfolio(
                "BTTUSDT",
                new BigDecimal("0.003869"),
                new BigDecimal("8726.517005")
        );

        user.getPortfolio().addCoinToPortfolio(
                "MATICUSDT",
                new BigDecimal("1.335"),
                new BigDecimal("15.05294063")
        );

        user.getPortfolio().addCoinToPortfolio(
                "ONEUSDT",
                new BigDecimal("0.12967"),
                new BigDecimal("154.25336116")
        );

        user.getPortfolio().addCoinToPortfolio(
                "XLMUSDT",
                new BigDecimal("0.3768"),
                new BigDecimal("53.01280070")
        );

        user.getPortfolio().addCoinToPortfolio(
                "LUNAUSDT",
                new BigDecimal("35.860"),
                new BigDecimal("0.89016859")
        );

        user.getPortfolio().addCoinToPortfolio(
                "XTZUSDT",
                new BigDecimal("5.414"),
                new BigDecimal("3.59801677")
        );

        user.getPortfolio().addCoinToPortfolio(
                "SRMUSDT",
                new BigDecimal("7.9465"),
                new BigDecimal("4.09661851")
        );

        user.getPortfolio().addCoinToPortfolio(
                "CELOUSDT",
                new BigDecimal("5.400"),
                new BigDecimal("1.80000000")
        );

        user.setUserPortfolioList(user.getService().addNewUserPortfolioToList(user.getPortfolio()));
    }
}

