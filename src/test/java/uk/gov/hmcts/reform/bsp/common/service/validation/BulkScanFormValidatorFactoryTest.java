package uk.gov.hmcts.reform.bsp.common.service.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;
import uk.gov.hmcts.reform.bsp.common.service.validation.impl.MockBulkScanFormValidator;
import uk.gov.hmcts.reform.bsp.common.service.validation.impl.MockBulkScanFormValidatorFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BulkScanFormValidatorFactoryTest {

    @InjectMocks
    private MockBulkScanFormValidatorFactory bulkScanFormValidatorFactory;

    @Before
    public void setup() {
        bulkScanFormValidatorFactory.initBean();
    }

    @Test
    public void getValidatorReturnsValidatorServiceObject() {
        BulkScanFormValidator validator = bulkScanFormValidatorFactory
            .getValidator(MockBulkScanFormValidatorFactory.MOCK_VALIDATOR);

        assertThat(validator, is(instanceOf(MockBulkScanFormValidator.class)));
    }

    @Test(expected = UnsupportedFormTypeException.class)
    public void getValidatorThrowsUnsupportedFormTypeException() {
        bulkScanFormValidatorFactory.getValidator("there is no validator for this name");
    }
}
