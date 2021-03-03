package com.example.demo.financialSystem;

import com.example.demo.managers.CurrencyJsonManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeCalculatorTest {
    @Test
    void count() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {

                // given
                CurrencyJsonManager currencyJsonManager = new CurrencyJsonManager();

                // when
                for(String key: currencyJsonManager.getKeys()){
                    ExchangeCalculator exchangeCalculator = new ExchangeCalculator(
                            new CashAmountWithCurrency("2.000", "EUR"),
                            new CashAmountWithCurrency("1.000", key)
                    );

                    String actual = new CashAmountWithCurrency(
                            currencyJsonManager.getMap().get(key).multiply(new BigDecimal("2.000")).toString(),
                            key
                    ).getCashAmount().toString();

                    String expected = exchangeCalculator.count(currencyJsonManager.getMap()).getCashAmount().toString().substring(0, actual.length());

                    // then
                    assertEquals(expected, actual);
                }
            }
        });
    }
}