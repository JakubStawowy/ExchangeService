package com.example.demo;

import com.example.demo.entities.UserAccount;
import com.example.demo.services.PasswordService;
import com.example.demo.entities.User;
import com.example.demo.entities.UserDetails;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
public class ExchangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService){

        return args -> {
            userService.getUserRepository().save(new User(
                    "miaudev1234@gmail.com",
                    PasswordService.getHashedPassword("xdd"),
                    new UserDetails("Miau", "Dev"),
                    new UserAccount("USD", new BigDecimal("1000.00")))
            );
            userService.getUserRepository().save(new User(
                    "exchangeservice1.1@gmail.com",
                    PasswordService.getHashedPassword("xdd"),
                    new UserDetails("Default", "User"),
                    new UserAccount("USD", new BigDecimal("1000.00")))
            );

        };
    }
}
