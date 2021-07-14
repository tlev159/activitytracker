package com.example.nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaxNumberValidator implements ConstraintValidator<IsValidTaxNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null &&
                value.trim().length() == 10) {
            try {
                int sum = 0;
                int multiplier = 1;
                String valueWithoutCDV = value.substring(0, 9);
                int cvd = Integer.parseInt(value.substring(9));
                for (Character c : valueWithoutCDV.toCharArray()) {
                    sum += Integer.parseInt(String.valueOf(c)) * multiplier;
                    multiplier++;
                }
                return (sum % 11 == cvd) ? true : false;
            } catch (IllegalStateException ise) {
                return false;
            }
        } else {
            return false;
        }
    }
}
