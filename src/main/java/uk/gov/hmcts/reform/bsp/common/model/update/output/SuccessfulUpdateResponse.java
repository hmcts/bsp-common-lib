package uk.gov.hmcts.reform.bsp.common.model.update.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.bsp.common.model.shared.CaseDetails;

import java.util.List;

@Data
@Builder
public class SuccessfulUpdateResponse {

    @JsonProperty("case_update_details")
    public final CaseDetails caseDetails;

    @JsonProperty("warnings")
    public final List<String> warnings;

    public SuccessfulUpdateResponse(
            CaseDetails caseDetails,
            List<String> warnings) {
        this.caseDetails = caseDetails;
        this.warnings = warnings;
    }
}