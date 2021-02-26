package com.example.demo.validators;

import org.springframework.validation.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MyValidator {
    protected void validate(ValidatorEnum validatorEnum, String value, String field, Errors errors){

        String patternReg = validatorEnum.getPattern();
        Pattern pattern = Pattern.compile(patternReg);
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches())
            errors.rejectValue(field, validatorEnum.getErrCode(), validatorEnum.getErrMessage());
    }
}
