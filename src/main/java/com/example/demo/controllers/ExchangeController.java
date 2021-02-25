package com.example.demo.controllers;

import com.example.demo.financialSystem.CashAmountWithCurrency;
import com.example.demo.financialSystem.ExchangeCalculator;
import com.example.demo.managers.JsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeController {

    private final JsonManager<String, BigDecimal> jsonManager;

    @Autowired
    public ExchangeController(JsonManager<String, BigDecimal> jsonManager) {
        this.jsonManager = jsonManager;
    }


    @PostMapping("/count")
    public CashAmountWithCurrency getExchange(@ModelAttribute("exchange") ExchangeCalculator exchangeCalculator){
        return exchangeCalculator.count(jsonManager.getMap());
    }
}
