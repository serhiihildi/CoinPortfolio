package com.CoinPortfolio.portfolio.service;

import com.CoinPortfolio.portfolio.model.Coin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class PortfolioService {

    private ArrayList<Coin> coinArrayList;

    public void setCoinArrayList(ArrayList<Coin> coinArrayList) {
        this.coinArrayList = coinArrayList;
    }

    public ArrayList<Coin> getCoinArrayList() {
        return coinArrayList;
    }

    private void getProfileProfit(ArrayList<Coin> coinArrayList){
        BigDecimal profitSum = BigDecimal.ZERO;
        for (Coin value : coinArrayList) {
            BigDecimal profit = value.getProfit();
            profitSum = profit.add(profitSum).plus(new MathContext(4,RoundingMode.HALF_UP));
        }
        System.out.printf("Общая прибыль по портфелю: $%s\n", profitSum);
    }

    private void getUserInvestmentNumber(ArrayList<Coin> coinArrayList){
        BigDecimal userInvestmentSum = BigDecimal.ZERO;
        for (Coin value : coinArrayList) {
            BigDecimal userInvestment = value.getUserInvestment();
            userInvestmentSum = userInvestment.add(userInvestmentSum).plus(new MathContext(4,RoundingMode.HALF_UP));
        }
        System.out.printf("Начальная цена портфеля: $%s\n", userInvestmentSum);
    }

    private void getCurrentUserPortfolioInvestmentNumber(ArrayList<Coin> coinArrayList){
        BigDecimal sumOfLastInvestmentCost = BigDecimal.ZERO;
        for (Coin value : coinArrayList) {
            BigDecimal lastInvestmentCost = value.getLastInvestmentCost();
            sumOfLastInvestmentCost = lastInvestmentCost
                    .add(sumOfLastInvestmentCost)
                    .plus(new MathContext(4,RoundingMode.HALF_UP));
        }
        System.out.printf("Актуальная стоимость портфеля: $%s\n", sumOfLastInvestmentCost);
    }

    private void showAllCoinAtList(ArrayList<Coin> userPortfolio) {
        for (Coin coin : userPortfolio) {
            coin.getCoinInfo();
        }
    }

    private void addedCoinToPortfolio(String symbol, BigDecimal userByuPrice,
                                      BigDecimal userCoinVolume, ArrayList<Coin> fatherCoinList,
                                      HashMap<String, BigDecimal> coinHashMap) {
        fatherCoinList.add(new Coin(
                symbol,
                userByuPrice,
                userCoinVolume,
                coinHashMap)
        );
    }

    public static void main(String[] args) {
        new PortfolioService().startService();
    }

    public void startService() {

        // Получаем карту со списком монет
        CoinService service = new CoinService();
        HashMap<String, BigDecimal> coinHashMap = service.getCoinHashMap();

        // Создаем список у пользователя
        ArrayList<Coin> userPortfolio = new ArrayList<>();

        // Добавляем монеты
        PortfolioService portfolioService = new PortfolioService();

        portfolioService.addedCoinToPortfolio(
                "ADAUSDT",
                new BigDecimal("2.300333333"),
                new BigDecimal("30.22200904"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "BTCUSDT",
                new BigDecimal("48268.405"),
                new BigDecimal("0.00303916"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "ETHUSDT",
                new BigDecimal("3747.48"),
                new BigDecimal("0.02400045"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "BNBUSDT",
                new BigDecimal("439.633"),
                new BigDecimal("0.19101295"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "DASHUSDT",
                new BigDecimal("209.525"),
                new BigDecimal("0.12000690"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "WAVESUSDT",
                new BigDecimal("27.790"),
                new BigDecimal("2.00997539"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "LTCUSDT",
                new BigDecimal("191.3"),
                new BigDecimal("0.26076419"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "THETAUSDT",
                new BigDecimal("6.9015"),
                new BigDecimal("3.30010344"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "DOTUSDT",
                new BigDecimal("31.090"),
                new BigDecimal("1.4567228"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "XRPUSDT",
                new BigDecimal("1.20295"),
                new BigDecimal("36.98182385"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "FLOWUSDT",
                new BigDecimal("22.860"),
                new BigDecimal("1.76823000"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "KAVAUSDT",
                new BigDecimal("7.1145"),
                new BigDecimal("5.60959568"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "NEOUSDT",
                new BigDecimal("53.780"),
                new BigDecimal("0.73926"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "SOLUSDT",
                new BigDecimal("144.883"),
                new BigDecimal("0.27222889"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "AXSUSDT",
                new BigDecimal("138.195"),
                new BigDecimal("0.22977"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "ATOMUSDT",
                new BigDecimal("24.145"),
                new BigDecimal("1.23874730"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "BTTUSDT",
                new BigDecimal("0.003869"),
                new BigDecimal("8726.517005"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "MATICUSDT",
                new BigDecimal("1.335"),
                new BigDecimal("15.05294063"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "ONEUSDT",
                new BigDecimal("0.12967"),
                new BigDecimal("154.25336116"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "XLMUSDT",
                new BigDecimal("0.3768"),
                new BigDecimal("53.01280070"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "LUNAUSDT",
                new BigDecimal("35.860"),
                new BigDecimal("0.89016859"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "XTZUSDT",
                new BigDecimal("5.414"),
                new BigDecimal("3.59801677"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "SRMUSDT",
                new BigDecimal("7.9465"),
                new BigDecimal("4.09661851"),
                userPortfolio,
                coinHashMap
        );

        new PortfolioService().addedCoinToPortfolio(
                "CELOUSDT",
                new BigDecimal("5.400"),
                new BigDecimal("1.80000000"),
                userPortfolio,
                coinHashMap
        );

        // Прокидываем монеты выше
        setCoinArrayList(userPortfolio);

        showAllCoinAtList(getCoinArrayList());
        getUserInvestmentNumber(getCoinArrayList());
        getCurrentUserPortfolioInvestmentNumber(getCoinArrayList());
        getProfileProfit(getCoinArrayList());
    }
}
