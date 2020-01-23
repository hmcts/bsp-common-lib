package uk.gov.hmcts.reform.bsp.common.model.shared.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExceptionRecord {

    @JsonProperty("case_type_id")
    private final String caseTypeId;

    @JsonProperty("id")
    private final String id;

    @JsonProperty("po_box")
    private final String poBox;

    @JsonProperty("form_type")
    private final String formType;

    @JsonProperty("ocr_data_fields")
    private final List<OcrDataField> ocrDataFields;
}
