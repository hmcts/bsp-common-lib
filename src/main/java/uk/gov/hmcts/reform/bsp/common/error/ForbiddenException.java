package uk.gov.hmcts.reform.bsp.common.error;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
