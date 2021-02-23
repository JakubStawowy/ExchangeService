package com.example.demo.validators;

import com.example.demo.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        validate(ValidatorEnum.EMAIL, user.getEmail(), "email", errors);
        validate(ValidatorEnum.PASSWORD, user.getPassword(), "password", errors);
//        validate(ValidatorEnum.USERNAME, user.getUserDetails().getName(), "username", errors);

    }

    private void validate(ValidatorEnum validatorEnum, String value, String field, Errors errors){

        String patternReg = validatorEnum.getPattern();
        Pattern pattern = Pattern.compile(patternReg);
        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches())
            errors.rejectValue(field, validatorEnum.getErrCode(), validatorEnum.getErrMessage());

    }
}
