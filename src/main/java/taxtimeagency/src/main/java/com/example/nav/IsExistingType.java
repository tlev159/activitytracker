package com.example.nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = TypeValidator.class)
public @interface IsExistingType {

    String message() default "Nem megfelelo ügykod!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
