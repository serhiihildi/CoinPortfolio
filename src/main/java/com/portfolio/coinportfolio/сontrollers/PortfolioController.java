package com.portfolio.coinportfolio.сontrollers;

import com.portfolio.coinportfolio.model.Coin;
import com.portfolio.coinportfolio.model.Portfolio;
import com.portfolio.coinportfolio.repo.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class PortfolioController {

    @Autowired
    private PortfolioRepo repo1;

    @GetMapping("/list_of_portfolio")
    public String greeting(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            Map<String, Object> map) {

        Iterable<Portfolio> portfolio;

        if (filter != null && !filter.isEmpty()) {
            portfolio = repo1.findByPortfolioName(filter);
        } else {
            portfolio = repo1.findAll();
        }

        model.addAttribute("portfolio", portfolio);
        model.addAttribute("filter", filter);

        map.put("portfolio", portfolio);
        return "PortfolioPage";
    }

    @PostMapping("/list_of_portfolio")
    public String addPortfolioToList(
            @RequestParam String portfolioName,
            @RequestParam ArrayList<Coin> coinList,
            Map<String, Object> model
    ) {

        Portfolio portfolio = new Portfolio(portfolioName, coinList);
        repo1.save(portfolio);

        Iterable<Portfolio> pf = repo1.findAll();
        model.put("portfolio", pf);

        return "PortfolioPage";
    }
}
