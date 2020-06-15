package uk.gov.hmcts.reform.bsp.common.service.transformation;

import lombok.extern.slf4j.Slf4j;
import uk.gov.hmcts.reform.bsp.common.error.UnsupportedFormTypeException;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.ExceptionRecord;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static uk.gov.hmcts.reform.bsp.common.config.BspCommonFields.BULK_SCAN_CASE_REFERENCE;

@Slf4j
public abstract class BulkScanFormTransformer {

    public Map<String, Object> transformIntoCaseData(ExceptionRecord exceptionRecord) throws UnsupportedFormTypeException {
        List<OcrDataField> ocrDataFields = exceptionRecord.getOcrDataFields();

        Map<String, Object> caseData = mapOcrFieldsToCaseData(ocrDataFields);

        // Need to store the Exception Record ID as part of the CCD data
        caseData.put(BULK_SCAN_CASE_REFERENCE, exceptionRecord.getId());

        caseData.putAll(transformAdditionalDataFromExceptionRecord(exceptionRecord));

        Map<String, Object> formSpecificMap = runFormSpecificTransformation(ocrDataFields);
        caseData.putAll(formSpecificMap);

        caseData = runPostMappingModification(caseData);

        return caseData;
    }

    protected Map<String, Object> transformAdditionalDataFromExceptionRecord(ExceptionRecord exceptionRecord) {
        return Collections.emptyMap();
    }

    protected abstract Map<String, String> getOcrToCCDMapping();

    protected Map<String, Object> runFormSpecificTransformation(List<OcrDataField> ocrDataFields) {
        return Collections.emptyMap();
    }

    protected Map<String, Object> runPostMappingModification(Map<String, Object> ccdTransformedFields) {
        return ccdTransformedFields;
    }

    private Map<String, Object> mapOcrFieldsToCaseData(List<OcrDataField> ocrDataFields) {
        Map<String, String> ocrToCCDMapping = getOcrToCCDMapping();

        return ocrDataFields.stream()
            .filter(ocrDataField -> ocrToCCDMapping.containsKey(ocrDataField.getName()))
            .peek(ocrDataField -> {
                if (ocrDataField.getValue() == null) {
                    log.info("Null value for {}", ocrDataField.getName());
                }
            })
            .collect(Collectors.toMap(
                ocrDataField -> ocrToCCDMapping.get(ocrDataField.getName()), OcrDataField::getValue
            ));
    }
}
