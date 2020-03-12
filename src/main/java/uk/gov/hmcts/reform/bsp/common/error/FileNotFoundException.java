package uk.gov.hmcts.reform.bsp.common.error;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String message) {
        super(message);
    }
}
