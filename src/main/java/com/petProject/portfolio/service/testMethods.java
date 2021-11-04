package com.petProject.portfolio.service;

public class testMethods {

    private String coinName;
    private float buyPrice;
    private float coinNumber;
    private float actualPrice;
    private float coinVolume;
    private float coinProfit;
    private float actualVolume;

    public void addedCoin(String coinName, float buyPrice, float coinNumber) {
        this.coinName = coinName;
        this.buyPrice = buyPrice;
        this.coinNumber = coinNumber;

        // По API подтягивает актуальны котировки
        actualPrice = takeActualPrice();

        //Высчитываем количество вложенных средств
        coinVolume = CoinVolume(midPrice(coinName, buyPrice), coinNumber(coinNumber));

        // Высчитываем актуальную стоимость актива
        actualVolume = actualVolume(coinVolume, actualPrice);

        coinProfit = profit(actualVolume, coinVolume);
    }

    private float profit(float actualVolume, float coinVolume) {
        return actualVolume - coinVolume;
    }

    private float actualVolume(float coinVolume, float actualPrice) {
        return coinVolume * actualPrice;
    }

    private float CoinVolume(float midPrice, float coinNumber) {
        return midPrice * coinNumber;
    }


    public float midPrice(String coinName, float buyPrice) {
        // сходить в бд
        // подтянуть в виде коллекции информацию о предыдущих покупках этой монеты (цены)
        // пройтись по коллекуции и сложить
        // разделить на количество покупок
        return buyPrice;
    }

    public float takeActualPrice() {
        return 0f;
    }

    public float coinNumber(float coinNumber) {
        return coinNumber;
    }



}
