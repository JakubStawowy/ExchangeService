package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.financialSystem.Calculator;
import com.example.demo.financialSystem.CashAmountWithCurrency;
import com.example.demo.financialSystem.ExchangeCalculator;
import com.example.demo.financialSystem.ExchangeCalculatorBuffer;
import com.example.demo.managers.JsonManager;
import com.example.demo.services.UserService;
import com.example.demo.validators.NumberFormatValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class ExchangeController {

    private final JsonManager<String, BigDecimal> jsonManager;
    private final NumberFormatValidator numberFormatValidator;
    private final UserService userService;

    @Autowired
    public ExchangeController(JsonManager<String, BigDecimal> jsonManager, NumberFormatValidator numberFormatValidator, UserService userService) {
        this.jsonManager = jsonManager;
        this.numberFormatValidator = numberFormatValidator;
        this.userService = userService;
    }

    @PostMapping("/json")
    @ResponseBody
    public CashAmountWithCurrency getExchange(@ModelAttribute("exchange") Calculator<String, BigDecimal> exchangeCalculator){
        return exchangeCalculator.count(jsonManager.getMap());
    }

    @PostMapping("/count")
    public String count(final @ModelAttribute("exchange") @Valid ExchangeCalculatorBuffer exchangeCalculatorBuffer,
                        final @CookieValue(value = "user_id") String id, Model model){

        Optional<User> optionalUser = userService.getUserRepository().findById(Long.valueOf(id));
        optionalUser.ifPresent(user -> {
            model.addAttribute("exchange", new ExchangeCalculatorBuffer());
            model.addAttribute("rates", jsonManager.getKeys());
            model.addAttribute("loggedUser", user);
        });

        if(!numberFormatValidator.checkStringParseToBigDecimal(exchangeCalculatorBuffer.getInitAmount())){
            model.addAttribute("message", "Wrong number format");
            return "home";
        }

        ExchangeCalculator exchangeCalculator = new ExchangeCalculator(
                new CashAmountWithCurrency(exchangeCalculatorBuffer.getInitAmount(), exchangeCalculatorBuffer.getInitCurrency()),
                new CashAmountWithCurrency("1.0000", exchangeCalculatorBuffer.getTargetCurrency())
        );

        model.addAttribute("result", "result: "+exchangeCalculator.count(jsonManager.getMap()));
        return "home";
    }
}
