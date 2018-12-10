package org.validation;

import static org.validation.Const.MAX_AMOUNT;
import static org.validation.Const.MIN_AMOUNT;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AmountValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountVaild {
    String message() default "ammount is not vaild";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // min value, value we support
    String min() default MIN_AMOUNT;

    // max date value we support
    String max() default MAX_AMOUNT;
}
