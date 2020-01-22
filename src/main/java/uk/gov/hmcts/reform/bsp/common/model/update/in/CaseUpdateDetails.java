package uk.gov.hmcts.reform.bsp.common.model.update.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class CaseUpdateDetails {

    @JsonProperty("case_type_id")
    private final String caseTypeId;

    @JsonProperty("case_data")
    private final Map<String, Object> caseData;

    public CaseUpdateDetails(
        String caseTypeId,
        Map<String, Object> caseData
    ) {
        this.caseTypeId = caseTypeId;
        this.caseData = caseData;
    }
}