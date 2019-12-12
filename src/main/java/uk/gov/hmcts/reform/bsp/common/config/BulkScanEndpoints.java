package uk.gov.hmcts.reform.bsp.common.config;

/**
 * All endpoints required by BSP phase 2.1 that should be implemented to fully support bulk scan process.
 * */
public class BulkScanEndpoints {
    public static final String VALIDATE = "/forms/{form-type}/validate-ocr";
    public static final String TRANSFORM = "/transform-exception-record";
    public static final String UPDATE = "/update-case";
}
