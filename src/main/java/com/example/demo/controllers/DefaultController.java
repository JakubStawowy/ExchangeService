package com.example.demo.controllers;

import com.example.demo.entities.Exchange;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

@Controller
public class DefaultController {

    private final UserService userService;

    @Autowired
    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, final @CookieValue(value = "user_id") String id){

        model.addAttribute("exchange", new Exchange(1.12f));
        Optional<User> user = userService.getUserRepository().findById(Long.valueOf(id));
        user.ifPresent(value -> model.addAttribute("loggedUser", value));

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
}
