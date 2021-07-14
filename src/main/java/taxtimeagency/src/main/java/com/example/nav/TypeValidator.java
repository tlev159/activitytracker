package com.example.nav;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<IsExistingType, String> {

    @Autowired
    NavService navService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return navService.hasTypeWithCode(value.trim());
    }
}
