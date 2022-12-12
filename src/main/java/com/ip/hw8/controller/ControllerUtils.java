package com.ip.hw8.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector =
                Collectors.toMap(fieldError -> fieldError.getField() + "Error",
                        FieldError::getDefaultMessage);
        Collector<ObjectError, ?, Map<String, String>> collector2 =
                Collectors.toMap(objectError -> objectError.getObjectName() + "Error",
                        ObjectError::getDefaultMessage);
        Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(collector);
        errors = bindingResult.getAllErrors().stream().collect(collector2);

        return errors;
    }
}
