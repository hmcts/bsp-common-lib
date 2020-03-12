package uk.gov.hmcts.reform.bsp.common.service;

import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;

import java.util.Map;

public abstract class BulkScanFormValidatorFactory {

    protected Map<String, BulkScanFormValidator> validators;

    public abstract void initBean();

    public BulkScanFormValidator getValidator(final String formType) throws UnsupportedFormTypeException {
        if (!validators.containsKey(formType)) {
            throw new UnsupportedFormTypeException(formType);
        }

        return validators.get(formType);
    }
}
