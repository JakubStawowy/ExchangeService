package com.example.demo.financialSystem;

import java.math.BigDecimal;
import java.util.Currency;

public class CashAmountWithCurrency {

    private BigDecimal cashAmount;
    private Currency currency;

    public CashAmountWithCurrency(String cashAmount, String currency){
        this.cashAmount = new BigDecimal(cashAmount);
        this.currency = Currency.getInstance(currency);
    }

    public CashAmountWithCurrency() {
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
