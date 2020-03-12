package uk.gov.hmcts.reform.bsp.common.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;

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
    public void getValidatorThrowsException() {
        bulkScanFormValidatorFactory.getValidator("there is not validator for this name");
    }
}
