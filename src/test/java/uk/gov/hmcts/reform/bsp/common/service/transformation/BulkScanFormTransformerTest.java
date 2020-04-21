package uk.gov.hmcts.reform.bsp.common.service.transformation;

import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.ExceptionRecord;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.ExampleBulkScanFormTransformer;
import uk.gov.hmcts.reform.bsp.common.service.transformation.impl.ExampleBulkScanFormTransformerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static uk.gov.hmcts.reform.bsp.common.config.BspCommonFields.BULK_SCAN_CASE_REFERENCE;

public class BulkScanFormTransformerTest {

    private static final String EX_RECORD_ID = "1234";

    private BulkScanFormTransformer bulkScanFormTransformer = new ExampleBulkScanFormTransformer();

    @Test
    public void transformIntoCaseDataShouldReturnMapOfCCDFields() {
        List<OcrDataField> input = Arrays.asList(
                new OcrDataField("OCR_Field1", "value1"),
                new OcrDataField("OCR_Field2", "value2"),
                new OcrDataField("OCR_Field3", "value3"),
                new OcrDataField("OCR_Field4", "value4"),
                new OcrDataField("OCR_Field5", "value5")
        );

        Map<String, Object> result = createExceptionRecord(input);

        assertThat(result.size(), is(7));
        assertThat(result.getOrDefault("CCD_Field1", ""), is("value1"));
        assertThat(result.getOrDefault("CCD_Field2", ""), is("value2"));
        assertThat(result.getOrDefault("CCD_Field3", ""), is("value3"));
        assertThat(result.getOrDefault("CCD_Field4", ""), is("value4"));
        assertThat(result.getOrDefault("CCD_Field5", ""), is("value5"));
        assertThat(result.getOrDefault(BULK_SCAN_CASE_REFERENCE, ""), is(EX_RECORD_ID));
    }

    @Test
    public void shouldTransformExceptionRecordDataAccordingly() {
        Map<String, Object> caseData = bulkScanFormTransformer.transformIntoCaseData(
                ExceptionRecord.builder().poBox("12345").ocrDataFields(emptyList()).build()
        );

        assertThat(caseData, hasEntry("transformedPoBox", "12345"));
    }

    @Test
    public void transformIntoCaseDataShouldIgnoreUnknownFields() {
        List<OcrDataField> input = Arrays.asList(
                new OcrDataField("this field will be ignored", "ignored value"),
                new OcrDataField("OCR_Field5", "value5")
        );

        Map<String, Object> result = createExceptionRecord(input);

        assertThat(result.size(), is(3));
        assertThat(result.getOrDefault("CCD_Field5", ""), is("value5"));
        assertThat(result.getOrDefault(BULK_SCAN_CASE_REFERENCE, ""), is(EX_RECORD_ID));
    }

    private Map<String, Object> createExceptionRecord(List<OcrDataField> input) {
        return bulkScanFormTransformer.transformIntoCaseData(
                ExceptionRecord.builder()
                        .formType(ExampleBulkScanFormTransformerFactory.TRANSFORMER_NAME)
                        .ocrDataFields(input)
                        .id(EX_RECORD_ID)
                        .build()
        );
    }

}