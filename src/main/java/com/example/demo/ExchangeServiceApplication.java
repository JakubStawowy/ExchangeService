package com.example.demo;

import com.example.demo.managers.JsonManager;
import com.example.demo.services.PasswordService;
import com.example.demo.entities.User;
import com.example.demo.entities.UserDetails;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExchangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService){

        return args -> {
            userService.getUserRepository().save(new User("user@email.com", PasswordService.getHashedPassword("xdd"), new UserDetails("aa", "bb")));
        };
    }
}
