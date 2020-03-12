package uk.gov.hmcts.reform.bsp.common.service.transformation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.MockBulkScanFormTransformer;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.MockBulkScanFormTransformerFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BulkScanFormTransformerFactoryTest {

    @InjectMocks
    private MockBulkScanFormTransformerFactory bulkScanFormTransformerFactory;

    @Before
    public void setup() {
        bulkScanFormTransformerFactory.init();
    }

    @Test
    public void getValidatorReturnsValidatorServiceObject() {
        BulkScanFormTransformer transformer = bulkScanFormTransformerFactory
            .getTransformer(MockBulkScanFormTransformerFactory.TRANSFORMER_NAME);

        assertThat(transformer, is(instanceOf(MockBulkScanFormTransformer.class)));
    }

    @Test(expected = UnsupportedFormTypeException.class)
    public void getTransformerThrowsUnsupportedFormTypeException() {
        bulkScanFormTransformerFactory.getTransformer("there is no transformer for this name");
    }
}
