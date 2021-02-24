package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.PasswordService;
import com.example.demo.services.UserService;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public RegisterController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userValidator);
    }

    @PostMapping("/register")
    public String register(final @ModelAttribute("user") @Valid User user, BindingResult result) throws NoSuchAlgorithmException {


        if(result.hasErrors()){
            return "register";
        }
        String hashedPassword = PasswordService.getHashedPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userService.getUserRepository().save(user);
        return "redirect:login";
    }
}
