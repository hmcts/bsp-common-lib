package uk.gov.hmcts.reform.bsp.common.model.update.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public class CaseDetails {

    @JsonProperty("case_data")
    private Map<String, Object> caseData;

    /**
     * This field is expected in the future, please see: https://tools.hmcts.net/jira/browse/BPS-968
     * but we can add it now as we `JsonIgnoreProperties(ignoreUnknown = true)`
     * */
    @JsonProperty("id")
    private String caseId;

    private String state;
}
