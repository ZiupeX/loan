package org.exposure;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.validation.TermVaild;

@Setter
@Getter
public class LoanExtendRequest {

    private Integer loanId;
    @TermVaild
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime term;
}
