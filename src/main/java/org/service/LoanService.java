package org.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.model.Loan;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final AtomicInteger atomicInteger = new AtomicInteger();
    private final List<Loan> loanList = new ArrayList();

    public Loan apply(final BigDecimal amount, final LocalDateTime term) throws Exception {
        final Loan loan = Loan.builder()
            .id(atomicInteger.getAndIncrement())
            .amount(amount)
            .term(term)
            .build();
        loanList.add(loan);
        return loan;
    }

    public Loan extend(final Integer loanId, final LocalDateTime extensionTerm) throws Exception {
        return loanList.stream()
            .filter(loan -> loan.getId().equals(loanId))
            .filter(loan -> loan.getTerm().isBefore(extensionTerm))
            .findAny()
            .orElseThrow(NullPointerException::new)
            .toBuilder()
            .term(extensionTerm)
            .build();
    }

}
