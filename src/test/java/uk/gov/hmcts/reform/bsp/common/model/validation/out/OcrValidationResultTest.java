package uk.gov.hmcts.reform.bsp.common.model.validation.out;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.model.validation.out.ValidationStatus.SUCCESS;

public class OcrValidationResultTest {

    @Test
    public void shouldHaveErrorsStatusWhenThereIsAnErrorMessage() {
        OcrValidationResult warningAndErrorMessagesResult = OcrValidationResult.builder()
            .addWarning("Warning message")
            .addError("Error message")
            .build();
        assertThat(warningAndErrorMessagesResult.getStatus(), is(ValidationStatus.ERRORS));

        OcrValidationResult errorMessagesOnlyResult = OcrValidationResult.builder().addError("Error message").build();
        assertThat(errorMessagesOnlyResult.getStatus(), is(ValidationStatus.ERRORS));
    }

    @Test
    public void shouldHaveWarningsStatusWhenThereIs_WarningMessage_ButNoErrorMessage() {
        OcrValidationResult warningMessagesOnlyResult = OcrValidationResult.builder().addWarning("Error message").build();
        assertThat(warningMessagesOnlyResult.getStatus(), is(ValidationStatus.WARNINGS));
    }

    @Test
    public void shouldHaveSuccessStatusWhenThereIs_NoWarningMessage_NorErrorMessage() {
        OcrValidationResult noMessagesResult = OcrValidationResult.builder().build();
        assertThat(noMessagesResult.getStatus(), is(SUCCESS));
    }

}