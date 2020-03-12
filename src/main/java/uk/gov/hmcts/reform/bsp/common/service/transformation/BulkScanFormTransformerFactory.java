package uk.gov.hmcts.reform.bsp.common.service.transformation;

import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;

import java.util.Map;

import static java.lang.String.format;

public abstract class BulkScanFormTransformerFactory {

    protected Map<String, BulkScanFormTransformer> bulkScanFormTransformerMap;

    public abstract void init();

    public BulkScanFormTransformer getTransformer(String formType) {
        if (!bulkScanFormTransformerMap.containsKey(formType)) {
            throw new UnsupportedFormTypeException(format("Form type \"%s\" is not supported.", formType));
        }

        return bulkScanFormTransformerMap.get(formType);
    }
}