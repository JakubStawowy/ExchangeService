package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.entities.UserAccount;
import com.example.demo.managers.CurrencyJsonManager;
import com.example.demo.managers.JsonManager;
import com.example.demo.services.EmailService;
import com.example.demo.services.PasswordService;
import com.example.demo.services.UserService;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes("registeredUser")
public class RegisterController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final EmailService emailService;
    private final JsonManager<String, BigDecimal> jsonManager;

    @Autowired
    public RegisterController(UserService userService, UserValidator userValidator,
                              EmailService emailService, CurrencyJsonManager jsonManager) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.emailService = emailService;
        this.jsonManager = jsonManager;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userValidator);
    }

    @PostMapping("/authorizeUser")
    public String register(final @ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("rates", jsonManager.getKeys());
            return "register";
        }

        String authorizationCode = emailService.generateRandomCode();
        emailService.sendEmail(
                EmailService.senderAddress,
                "Confirm registration for user "+user.getUserDetails().getName(),
                "Authorization code: "+authorizationCode,
                user.getEmail()
        );

        user.setAuthorizationCode(authorizationCode);
        model.addAttribute("registeredUser", user);
        model.addAttribute("authorizedUser", new User());

        return "authorizeUser";
    }

    @GetMapping("/registerUser")
    public String registerUser(final @SessionAttribute("registeredUser") User user, final @ModelAttribute("authorizedUser") User authorizedUser,
                               HttpSession session, Model model) throws NoSuchAlgorithmException {

        session.removeAttribute("registeredUser");

        if(user.getAuthorizationCode().equals(authorizedUser.getAuthorizationCode())){

            String hashedPassword = PasswordService.getHashedPassword(user.getPassword());
            user.setPassword(hashedPassword);
            user.getUserAccount().setBalance(new BigDecimal("0.0000"));
            userService.getUserRepository().save(user);
            model.addAttribute("user", new User());
            model.addAttribute("message", "User registered succesfully!");

            return "login";
        }

        model.addAttribute("message", "Wrong authorization code. Try again");

        return "authorizeUser";

    }
}
