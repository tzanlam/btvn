package com.vti.validation.Account;

import com.vti.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountUsernameNotExistsValidator implements ConstraintValidator<AccountUsernameNotExists, String> {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !accountService.isUsernameExists(username);
    }
}
