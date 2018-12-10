package org.exposure;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.validation.AmountVaild;
import org.validation.TeramAndAmountVaild;
import org.validation.TermVaild;

@Getter
@Setter
@TeramAndAmountVaild
public class LoanApplyRequest {

    @AmountVaild
    private BigDecimal amount;

    @TermVaild
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime term;

}
