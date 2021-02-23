package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
