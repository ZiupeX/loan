package org.exposure;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoanExtendResponse {
    private final String message;
    private String term;
}
