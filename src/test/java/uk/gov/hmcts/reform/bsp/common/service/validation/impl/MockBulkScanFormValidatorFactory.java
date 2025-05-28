package uk.gov.hmcts.reform.bsp.common.service.validation.impl;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.bsp.common.service.validation.BulkScanFormValidatorFactory;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class MockBulkScanFormValidatorFactory extends BulkScanFormValidatorFactory {

    public static final String MOCK_VALIDATOR = "mockValidator";

    @Override
    @PostConstruct
    public void initBean() {
        validators = new HashMap<>();
        validators.put(MOCK_VALIDATOR, new MockBulkScanFormValidator());
    }
}
