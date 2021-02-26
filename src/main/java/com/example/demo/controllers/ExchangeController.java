package com.example.demo.controllers;

import com.example.demo.financialSystem.Calculator;
import com.example.demo.financialSystem.CashAmountWithCurrency;
import com.example.demo.financialSystem.ExchangeCalculator;
import com.example.demo.financialSystem.ExchangeCalculatorBuffer;
import com.example.demo.managers.JsonManager;
import com.example.demo.validators.CashValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class ExchangeController {

    private final JsonManager<String, BigDecimal> jsonManager;
    private final CashValidator validator;

    @Autowired
    public ExchangeController(JsonManager<String, BigDecimal> jsonManager, CashValidator validator) {
        this.jsonManager = jsonManager;
        this.validator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(validator);
    }

    @PostMapping("/json")
    @ResponseBody
    public CashAmountWithCurrency getExchange(@ModelAttribute("exchange") Calculator<String, BigDecimal> exchangeCalculator){
        return exchangeCalculator.count(jsonManager.getMap());
    }

    @PostMapping("/count")
    public String count(final @ModelAttribute("exchange") @Valid ExchangeCalculatorBuffer exchangeCalculatorBuffer, Model model, BindingResult result){

        if(result.hasErrors()){
            return "redirect:home";
        }
        ExchangeCalculator exchangeCalculator = new ExchangeCalculator(
                new CashAmountWithCurrency(exchangeCalculatorBuffer.getInitAmount(), exchangeCalculatorBuffer.getInitCurrency()),
                new CashAmountWithCurrency("1.0000", exchangeCalculatorBuffer.getTargetCurrency())
        );
        model.addAttribute("result", "result: "+exchangeCalculator.count(jsonManager.getMap()));
        model.addAttribute("exchange", new ExchangeCalculatorBuffer());
        model.addAttribute("rates", jsonManager.getKeys());

        return "home";
    }
}
