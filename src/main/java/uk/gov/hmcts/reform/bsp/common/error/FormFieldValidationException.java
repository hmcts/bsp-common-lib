package uk.gov.hmcts.reform.bsp.common.error;

public class FormFieldValidationException extends RuntimeException {

    public FormFieldValidationException(String validationErrorMessage) {
        super(validationErrorMessage);
    }

}