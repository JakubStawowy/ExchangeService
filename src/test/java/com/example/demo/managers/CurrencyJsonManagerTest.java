package com.example.demo.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyJsonManagerTest {

    @Test
    void getMap() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {

                // given
                CurrencyJsonManager currencyJsonManager = new CurrencyJsonManager();

                // when
                Map<String, BigDecimal> currencyWithAmountMap = currencyJsonManager.getMap();

                // then
                assertFalse(currencyWithAmountMap.isEmpty());
            }
        });
    }

    @Test
    void getKeys() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {

                // given
                CurrencyJsonManager currencyJsonManager = new CurrencyJsonManager();

                // when
                List<String> currencies = currencyJsonManager.getKeys();

                // then
                for(String currency: currencies){
                    assertEquals(3, currency.length());
                }
            }
        });
    }
}