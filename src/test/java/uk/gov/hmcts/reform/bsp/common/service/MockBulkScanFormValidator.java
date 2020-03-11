package uk.gov.hmcts.reform.bsp.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBulkScanFormValidator extends BulkScanFormValidator {

    @Override
    protected List<String> getMandatoryFields() {
        return new ArrayList<>();
    }

    @Override
    protected List<String> runPostProcessingValidation(Map<String, String> fieldsMap) {
        return new ArrayList<>();
    }

    @Override
    protected Map<String, List<String>> getAllowedValuesPerField() {
        return new HashMap<>();
    }
}
