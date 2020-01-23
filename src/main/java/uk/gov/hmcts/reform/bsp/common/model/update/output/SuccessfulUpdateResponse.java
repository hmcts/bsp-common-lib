package uk.gov.hmcts.reform.bsp.common.model.update.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.bsp.common.model.shared.CaseDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SuccessfulUpdateResponse {

    @JsonProperty("case_update_details")
    private final CaseDetails caseDetails;

    @JsonProperty("warnings")
    @Builder.Default
    private List<String> warnings = new ArrayList<>();
}
