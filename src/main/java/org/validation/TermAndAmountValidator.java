package org.validation;

import static org.validation.Const.MAX_AMOUNT;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.exposure.LoanApplyRequest;

public class TermAndAmountValidator implements ConstraintValidator<TeramAndAmountVaild, LoanApplyRequest> {

    @Override
    public boolean isValid(LoanApplyRequest value, ConstraintValidatorContext context) {
        return !(checkTermRange(value) && isMaxAmount(value));
    }

    private boolean isMaxAmount(LoanApplyRequest value){
      final BigDecimal max = new BigDecimal(MAX_AMOUNT);
      return value.getAmount().compareTo(max) == 0;
    }

    private boolean checkTermRange(LoanApplyRequest value){
        return value.getTerm().getHour() < 0 && value.getTerm().getHour() > 6;
    }


}
