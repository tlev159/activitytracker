package com.example.nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = TaxNumberValidator.class)
public @interface IsValidTaxNumber {

    String message() default "nem jo az adoazonosito!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
