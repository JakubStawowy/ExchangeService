package com.example.demo.financialSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ExchangeCalculator implements Calculator<String, BigDecimal>{

    private CashAmountWithCurrency initValue;
    private CashAmountWithCurrency targetValue;

    public ExchangeCalculator() {
        initValue = new CashAmountWithCurrency();
        targetValue = new CashAmountWithCurrency();
    }


    public ExchangeCalculator(CashAmountWithCurrency initValue, CashAmountWithCurrency targetValue){
        this.initValue = initValue;
        this.targetValue = targetValue;
    }

    public void setInitValue(CashAmountWithCurrency initValue) {
        this.initValue = initValue;
    }

    public void setTargetValue(CashAmountWithCurrency targetValue) {
        this.targetValue = targetValue;
    }

    public CashAmountWithCurrency getInitValue() {
        return initValue;
    }

    public CashAmountWithCurrency getTargetValue() {
        return targetValue;
    }

    @Override
    public CashAmountWithCurrency count(Map<String, BigDecimal> currencyMap) {

        BigDecimal initCashAmount = initValue.getCashAmount();
        BigDecimal initCurrencyRate = currencyMap.get(initValue.getCurrency().toString());
        BigDecimal targetCurrencyRate = currencyMap.get(targetValue.getCurrency().toString());

        targetValue.setCashAmount(initCashAmount.multiply(targetCurrencyRate).divide(initCurrencyRate, RoundingMode.HALF_EVEN));

        return targetValue;
    }
}
