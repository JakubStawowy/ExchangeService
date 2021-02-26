package com.example.demo.validators;

import com.example.demo.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator extends MyValidator implements Validator {

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
}
