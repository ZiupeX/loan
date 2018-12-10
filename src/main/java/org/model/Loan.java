package org.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Loan {
    private Integer id;
    private BigDecimal amount;
    private LocalDateTime term;
    public BigDecimal totalAmount() {
        return (amount.multiply(new BigDecimal(1.10)).setScale(2, RoundingMode.HALF_UP));
    }
}
