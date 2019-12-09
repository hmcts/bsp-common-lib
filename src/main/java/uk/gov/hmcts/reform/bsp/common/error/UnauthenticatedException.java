package uk.gov.hmcts.reform.bsp.common.error;

public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException(String message) {
        super(message);
    }
}
