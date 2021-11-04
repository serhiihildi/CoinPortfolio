package com.petProject.portfolio;

import com.petProject.portfolio.view.CoinView;
import com.petProject.portfolio.сontrollers.CoinController;
import com.petProject.portfolio.model.Coin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
		Coin model = retrieveCoinFromDatabase();
		CoinView view = new CoinView();

		CoinController controller = new CoinController(model, view);

		controller.updateView();

		controller.setCoin("BTCADA");
		controller.updateView();
	}

	private static Coin retrieveCoinFromDatabase() {
		Coin coin = new Coin();
		coin.setCoinName("BTCUSDT");
		coin.setCoinCurrentPrice(60000.10f);
		return coin;

	}

}
