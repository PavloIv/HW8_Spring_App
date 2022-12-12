package com.ip.hw8.anotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented

@Constraint(validatedBy = DuplicateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Duplicate {
    String message() default "User with this email already exist";

    String id();

    String email();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
