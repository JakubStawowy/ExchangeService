package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(final @ModelAttribute("user") @Valid User user) throws NoSuchAlgorithmException {

        String hashedPassword = PasswordHasher.getHashedPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userService.getUserRepository().save(user);
        return "redirect:login";
    }
}
