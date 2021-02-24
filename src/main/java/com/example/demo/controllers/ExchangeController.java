package com.example.demo.controllers;

import com.example.demo.entities.Exchange;
import com.example.demo.managers.JsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExchangeController {

    private final JsonManager jsonManager;

    @Autowired
    public ExchangeController(JsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    @PostMapping("/count")
    public Exchange count(@ModelAttribute("exchange") Exchange exchange){

        Map<String, Float> currencyMap = jsonManager.getCurrencyMap();

        Float valueFrom = currencyMap.get(exchange.getCurrencyFrom());
        Float valueTo = currencyMap.get(exchange.getCurrencyTo());

        exchange.setResult((exchange.getValue()/valueFrom) * valueTo);

        return exchange;
    }
}
