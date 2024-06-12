package com.vti.validation.Account;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountUsernameNotExistsValidator.class)
public @interface AccountUsernameNotExists {
    String message() default "Username exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
