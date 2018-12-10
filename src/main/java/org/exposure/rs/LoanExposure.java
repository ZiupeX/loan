package org.exposure.rs;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.exposure.LoanApplyRequest;
import org.exposure.LoanApplyResponse;
import org.exposure.LoanExtendRequest;
import org.exposure.LoanExtendResponse;
import org.model.Loan;
import org.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanExposure {

    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.POST, path = "/apply")
    public LoanApplyResponse apply(@Valid final LoanApplyRequest loanApplyRequest) {
        try {
            final Loan loan = loanService.apply(loanApplyRequest.getAmount(), loanApplyRequest.getTerm());
            return LoanApplyResponse.builder()
                .message("loan with id " + loan.getId() + " has been created")
                .term("payment deadline: " + loan.getTerm())
                .totalAmount("the total cost of the loan: " + loan.totalAmount())
                .build();
        } catch (Exception ex) {
            return LoanApplyResponse.builder()
                .message("loan creation failed")
                .build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/extend")
    public LoanExtendResponse extend(@Valid final LoanExtendRequest loanExtendRequest) {
        try {
            final Loan loan = loanService.extend(loanExtendRequest.getLoanId(), loanExtendRequest.getTerm());
            return LoanExtendResponse.builder()
                .message("loan with id " + loan.getId() + " has been extended")
                .term("new extended payment deadline: " + loan.getTerm())
                .build();
        } catch (Exception ex) {
            return LoanExtendResponse.builder()
                .message("loan extension failed")
                .build();
        }
    }

}
