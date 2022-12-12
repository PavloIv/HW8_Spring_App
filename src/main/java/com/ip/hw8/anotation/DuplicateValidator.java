package com.ip.hw8.anotation;

import com.ip.hw8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicateValidator implements ConstraintValidator<Duplicate, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(Duplicate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        try {
            return userRepository.findByEmail(email) == null ;
        } catch (NullPointerException e) {
            return true;
        }
    }
}
