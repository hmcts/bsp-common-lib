package uk.gov.hmcts.reform.bsp.common.service.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;
import uk.gov.hmcts.reform.bsp.common.model.validation.out.OcrValidationResult;
import uk.gov.hmcts.reform.bsp.common.service.validation.impl.MockBulkScanFormValidator;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.utils.TestUtils.isEmptyList;

@RunWith(MockitoJUnitRunner.class)
public class BulkScanFormValidatorTest {

    @InjectMocks
    private MockBulkScanFormValidator bulkScanFormValidator;

    @Test
    public void validateBulkScanFormShouldReturnEmptyListOfWarnings() {
        OcrValidationResult result = bulkScanFormValidator.validateBulkScanForm(new ArrayList<OcrDataField>());

        assertThat(result.getWarnings(), isEmptyList());
    }
}
