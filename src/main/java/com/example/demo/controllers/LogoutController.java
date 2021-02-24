package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response){

        Cookie cookie = new Cookie("user_id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.invalidate();
        return "redirect:login";
    }
}
