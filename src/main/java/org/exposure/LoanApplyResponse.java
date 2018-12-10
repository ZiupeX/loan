package org.exposure;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class LoanApplyResponse {
    private String message;
    private String totalAmount;
    private String term;
}
