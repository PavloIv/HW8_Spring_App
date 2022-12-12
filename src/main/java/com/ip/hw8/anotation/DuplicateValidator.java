package com.ip.hw8.anotation;

import com.ip.hw8.entity.User;
import com.ip.hw8.repository.UserRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicateValidator implements ConstraintValidator<Duplicate, Object> {
    @Autowired
    UserRepository userRepository;
    private String id;
    private String email;

    @Override
    public void initialize(Duplicate constraintAnnotation) {
        this.id = constraintAnnotation.id();
        this.email = constraintAnnotation.email();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object idValue = new BeanWrapperImpl(value).getPropertyValue(String.valueOf(id));
            Object emailValue = new BeanWrapperImpl(value).getPropertyValue(email);
            User userAudit = userRepository.findByEmail((String) emailValue);
            if (idValue == null) {
                return userAudit == null;
            } else {
                return userAudit == null || userAudit.getId() == idValue;
            }
        } catch (NullPointerException e) {
            return true;
        }
    }
}
