package com.vti.validation.Department;

import com.vti.service.DepartmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String> {
    @Autowired
    private DepartmentService departmentService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !departmentService.isUsernameExist(s);
    }
}
