package com.example.demo.controllers;

import com.example.demo.entities.ErrorMessage;
import com.example.demo.entities.Exchange;
import com.example.demo.entities.Log;
import com.example.demo.entities.User;
import com.example.demo.services.LogService;
import com.example.demo.services.PasswordService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes("loggedUser")
public class LoginController {

    private final UserService userService;
    private final LogService logService;

    @Autowired
    public LoginController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping("/login")
    public String login(final @ModelAttribute("user") User user, Model model, final HttpSession session) throws NoSuchAlgorithmException {

        User loggedUser = userService.getUserRepository().getUserByEmailAndPassword(user.getEmail(), PasswordService.getHashedPassword(user.getPassword()));

        if(loggedUser != null){

            Log log = new Log(session.getId(), loggedUser);
            logService.getLogRepository().save(log);
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("exchange", new Exchange(1.12f));

            return "redirect:home";
        }
        else{
            model.addAttribute("message", new ErrorMessage("Wrong email or password"));
            return "login";

        }

    }
}
