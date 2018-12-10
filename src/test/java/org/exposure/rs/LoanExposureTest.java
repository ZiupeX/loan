package org.exposure.rs;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.validation.Const.MIN_TERM;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.exposure.LoanApplyRequest;
import org.exposure.LoanApplyResponse;
import org.exposure.LoanExtendRequest;
import org.exposure.LoanExtendResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.model.Loan;
import org.service.LoanService;

@RunWith(MockitoJUnitRunner.class)
public class LoanExposureTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanExposure loanExposure;

    @Test
    public void shouldApplyLoan() throws Exception {
        final BigDecimal amount = new BigDecimal("2.22");
        final LocalDateTime term = LocalDateTime.parse(MIN_TERM);
        final LoanApplyRequest loanApplyRequest = new LoanApplyRequest();
        loanApplyRequest.setAmount(amount);
        loanApplyRequest.setTerm(term);
        final Loan loan = Loan.builder()
            .amount(amount)
            .term(term)
            .build();

        when(loanService.apply(any(),any())).thenReturn(loan);

        final LoanApplyResponse response = loanExposure.apply(loanApplyRequest);
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(LoanApplyResponse.class);
        assertThat(response.getMessage().endsWith("has been created")).isTrue();
    }

    @Test
    public void shouldNotApplyLoan() throws Exception {
        final BigDecimal amount = new BigDecimal("2.22");
        final LocalDateTime term = LocalDateTime.parse(MIN_TERM);
        final LoanApplyRequest loanApplyRequest = new LoanApplyRequest();
        loanApplyRequest.setAmount(amount);
        loanApplyRequest.setTerm(term);
        final Loan loan = Loan.builder()
            .term(term)
            .build();

        when(loanService.apply(any(),any())).thenReturn(loan);

        final LoanApplyResponse response = loanExposure.apply(loanApplyRequest);
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(LoanApplyResponse.class);
        assertThat(response.getMessage().endsWith("loan creation failed")).isTrue();
    }

    @Test
    public void shouldExtendLoan() throws Exception {
        final LoanExtendRequest loanExtendRequest = new LoanExtendRequest();
        loanExtendRequest.setTerm(LocalDateTime.now());
        final Loan loan = Loan.builder()
            .term(LocalDateTime.now())
            .build();
        when(loanService.extend(any(),any())).thenReturn(loan);

        final LoanExtendResponse response = loanExposure.extend(loanExtendRequest);
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(LoanExtendResponse.class);
        assertThat(response.getMessage().endsWith("has been extended")).isTrue();
    }

    @Test
    public void shouldNotExtendLoan() {
        final LoanExtendRequest loanExtendRequest = new LoanExtendRequest();
        loanExtendRequest.setTerm(LocalDateTime.now());

        final LoanExtendResponse response = loanExposure.extend(loanExtendRequest);
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(LoanExtendResponse.class);
        assertThat(response.getMessage()).isEqualTo("loan extension failed");
    }
}