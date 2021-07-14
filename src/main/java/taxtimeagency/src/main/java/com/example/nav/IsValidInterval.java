package com.example.nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = IntervalValidator.class)
public @interface IsValidInterval {


    String message() default "Az intervallumhoz tartozo datumok nem megfeleloek!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
