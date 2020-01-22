package uk.gov.hmcts.reform.bsp.common.model.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseDetails {

    @JsonProperty("id")
    private final String caseId;

    @JsonProperty("case_type_id")
    private final String caseTypeId;

    @JsonProperty("case_data")
    private final Map<String, Object> caseData;

    public CaseDetails(
            String caseId,
            String caseTypeId,
            Map<String, Object> caseData
    ) {
        this.caseId = caseId;
        this.caseTypeId = caseTypeId;
        this.caseData = caseData;
    }
}