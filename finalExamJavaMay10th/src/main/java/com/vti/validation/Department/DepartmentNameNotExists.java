package com.vti.validation.Department;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentNameNotExistsValidator.class)
public @interface DepartmentNameNotExists {
    String message() default "Department name not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
