package uk.gov.hmcts.reform.bsp.common.service.transformation.impl;

import uk.gov.hmcts.reform.bsp.common.service.transformation.BulkScanFormTransformer;

import java.util.HashMap;
import java.util.Map;

public class MockBulkScanFormTransformer extends BulkScanFormTransformer {

    @Override
    protected Map<String, String> getOcrToCCDMapping() {
        Map<String, String> exceptionRecordToCcdFieldsMap = new HashMap<>();

        exceptionRecordToCcdFieldsMap.put("OCR_Field1", "CCD_Field1");
        exceptionRecordToCcdFieldsMap.put("OCR_Field2", "CCD_Field2");
        exceptionRecordToCcdFieldsMap.put("OCR_Field3", "CCD_Field3");
        exceptionRecordToCcdFieldsMap.put("OCR_Field4", "CCD_Field4");
        exceptionRecordToCcdFieldsMap.put("OCR_Field5", "CCD_Field5");

        return exceptionRecordToCcdFieldsMap;
    }
}
