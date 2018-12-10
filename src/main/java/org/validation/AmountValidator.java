package org.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<AmountVaild, BigDecimal> {

    private AmountVaild constraintAnnotation;

    @Override
    public void initialize(AmountVaild constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        final BigDecimal min = new BigDecimal(constraintAnnotation.min());
        final BigDecimal max = new BigDecimal(constraintAnnotation.max());
        return value != null && (value.compareTo(min) > 0 && value.compareTo(max) < 0);
    }
}
