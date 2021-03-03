package com.example.demo.controllers;

import com.example.demo.services.EmailService;
import com.example.demo.services.LogService;
import com.example.demo.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DefaultController.class)
class DefaultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultController defaultController;

    @MockBean
    private ExchangeController exchangeController;

    @MockBean
    private LoginController loginController;

    @MockBean
    private RegisterController registerController;

    @MockBean
    private UserService userService;

    @MockBean
    private TransactionController transactionController;

    @MockBean
    private EmailService emailService;

    @MockBean
    private LogService logService;

    @Test
    void index() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void login() {
    }

    @Test
    void home() {
    }

    @Test
    void register() {
    }

    @Test
    void test1() {
    }

    @Test
    void payment() {
    }

    @Test
    void authorizeTransaction() {
    }

    @Test
    void errorFallback() {
    }

    @Test
    void messagePage() {
    }
}