package com.example.demo.controllers;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MissingRequestCookieException.class)
    public String cookieFallback(){
        return "redirect:login";
    }

    @ExceptionHandler(BindException.class)
    public String validationFallback(){
        return "redirect:home";
    }

}
