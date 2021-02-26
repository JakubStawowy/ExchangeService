package com.example.demo.validators;

import com.example.demo.financialSystem.ExchangeCalculatorBuffer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CashValidator extends MyValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ExchangeCalculatorBuffer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ExchangeCalculatorBuffer exchangeCalculatorBuffer = (ExchangeCalculatorBuffer) o;

        validate(ValidatorEnum.AMOUNT, exchangeCalculatorBuffer.getInitAmount(), "initAmount", errors);
        validate(ValidatorEnum.CURRENCY, exchangeCalculatorBuffer.getInitCurrency(), "initCurrency", errors);
        validate(ValidatorEnum.CURRENCY, exchangeCalculatorBuffer.getTargetCurrency(), "targetCurrency", errors);
    }
}
