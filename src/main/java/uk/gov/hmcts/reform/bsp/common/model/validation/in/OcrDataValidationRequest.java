package uk.gov.hmcts.reform.bsp.common.model.validation.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class OcrDataValidationRequest {

    @ApiModelProperty(value = "List of ocr data fields to be validated.", required = true)
    @NotEmpty
    private final List<OcrDataField> ocrDataFields;

    public OcrDataValidationRequest(
            @JsonProperty("ocr_data_fields") List<OcrDataField> ocrDataFields
    ) {
        this.ocrDataFields = ocrDataFields;
    }
}
