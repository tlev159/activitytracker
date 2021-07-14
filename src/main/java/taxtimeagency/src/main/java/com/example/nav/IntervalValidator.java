package com.example.nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class IntervalValidator implements ConstraintValidator<IsValidInterval, CreateAppointmentCommand> {

    @Override
    public boolean isValid(CreateAppointmentCommand command, ConstraintValidatorContext context) {
        return command.getStart().isBefore(command.getEnd());
    }
}
