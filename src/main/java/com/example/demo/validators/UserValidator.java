package com.example.demo.validators;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class UserValidator extends MyValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

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

        if (!user.getPassword().equals(user.getConfirmedPassword()))
            errors.rejectValue("password", "err_code", "Confirmed password incorrect");

        User potentialUser = userService.getUserRepository().getUserByEmail(user.getEmail());
        if(potentialUser != null){
            errors.rejectValue("email", "err_code", "User with this email already exists");
        }
    }
}
