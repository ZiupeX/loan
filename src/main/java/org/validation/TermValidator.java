package org.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TermValidator implements ConstraintValidator<TermVaild, LocalDateTime> {

    private TermVaild constraintAnnotation;

    @Override
    public void initialize(TermVaild constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        final LocalDateTime min = LocalDateTime.parse(constraintAnnotation.min());
        final LocalDateTime max = LocalDateTime.parse(constraintAnnotation.max());
        return value != null && (value.isAfter(min) && value.isBefore(max));

    }
}
