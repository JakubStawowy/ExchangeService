package com.example.demo.controllers;

import com.example.demo.entities.TransactionBuffer;
import com.example.demo.entities.TransactionPayment;
import com.example.demo.financialSystem.ExchangeCalculator;
import com.example.demo.entities.User;
import com.example.demo.financialSystem.ExchangeCalculatorBuffer;
import com.example.demo.managers.CurrencyJsonManager;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class DefaultController {

    private final UserService userService;
    private final CurrencyJsonManager jsonManager;

    @Autowired
    public DefaultController(UserService userService, CurrencyJsonManager jsonManager) {
        this.userService = userService;
        this.jsonManager = jsonManager;
    }

    @GetMapping({"/", "*"})
    public String index(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, "user_id");
        if(cookie != null)
            return "redirect:home";
        else
            return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, final @CookieValue(value = "user_id") String id){

        model.addAttribute("exchange", new ExchangeCalculatorBuffer());
        model.addAttribute("rates", jsonManager.getKeys());
        Optional<User> user = userService.getUserRepository().findById(Long.valueOf(id));
        user.ifPresent(value -> model.addAttribute("loggedUser", value));

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/registerTransaction")
    public String test(Model model){
        model.addAttribute("transactionBuffer", new TransactionBuffer());
        return "payment";
    }

    @GetMapping("payment")
    public String payment(){
        return "payment";
    }

    @GetMapping("/authorizeTransaction")
    public String authorizeTransaction(Model model, @ModelAttribute("transactionBuffer") TransactionBuffer transactionBuffer){
        model.addAttribute("transactionBuffer", transactionBuffer);
        return "authorize";
    }
}
