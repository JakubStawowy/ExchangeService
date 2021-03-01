package com.example.demo.validators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NumberFormatValidator {
    public Boolean checkStringParseToBigDecimal(String value){
        try{
            new BigDecimal(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
