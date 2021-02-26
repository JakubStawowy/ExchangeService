package com.example.demo.financialSystem;

public class ExchangeCalculatorBuffer {

    private String initAmount;
    private String initCurrency;
    private String targetCurrency;

    public ExchangeCalculatorBuffer(String initAmount, String initCurrency, String targetCurrency) {
        this.initAmount = initAmount;
        this.initCurrency = initCurrency;
        this.targetCurrency = targetCurrency;
    }

    public ExchangeCalculatorBuffer() {
    }

    public String getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(String initAmount) {
        this.initAmount = initAmount;
    }

    public String getInitCurrency() {
        return initCurrency;
    }

    public void setInitCurrency(String initCurrency) {
        this.initCurrency = initCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
