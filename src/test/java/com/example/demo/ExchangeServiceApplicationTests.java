package com.example.demo;

import com.example.demo.controllers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeServiceApplicationTests {

    @Autowired
    private DefaultController defaultController;

    @Autowired
    private ExchangeController exchangeController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private RegisterController registerController;

    @Autowired
    private LogoutController logoutController;

    @Autowired
    private TransactionController transactionController;

    @Test
    void contextLoads() {
        assertThat(defaultController).isNotNull();
        assertThat(exchangeController).isNotNull();
        assertThat(loginController).isNotNull();
        assertThat(registerController).isNotNull();
        assertThat(logoutController).isNotNull();
        assertThat(transactionController).isNotNull();
    }

}
