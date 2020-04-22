package uk.gov.hmcts.reform.bsp.common.service.transformation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.ExampleBulkScanFormTransformer;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.ExampleBulkScanFormTransformerFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BulkScanFormTransformerFactoryTest {

    private BulkScanFormTransformerFactory bulkScanFormTransformerFactory = new ExampleBulkScanFormTransformerFactory();

    @Before
    public void setup() {
        bulkScanFormTransformerFactory.init();
    }

    @Test
    public void getValidatorReturnsValidatorServiceObject() {
        BulkScanFormTransformer transformer = bulkScanFormTransformerFactory
            .getTransformer(ExampleBulkScanFormTransformerFactory.TRANSFORMER_NAME);

        assertThat(transformer, is(instanceOf(ExampleBulkScanFormTransformer.class)));
    }

    @Test(expected = UnsupportedFormTypeException.class)
    public void getTransformerThrowsUnsupportedFormTypeException() {
        bulkScanFormTransformerFactory.getTransformer("there is no transformer for this name");
    }

}