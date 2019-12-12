package uk.gov.hmcts.reform.bsp.common.model.update.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.bsp.common.model.transformation.output.CaseUpdateDetails;

import java.util.List;

@Data
@Builder
public class SuccessfulUpdateResponse {

    @JsonProperty("case_update_details")
    public final CaseUpdateDetails caseUpdateDetails;

    @JsonProperty("warnings")
    public final List<String> warnings;

    public SuccessfulUpdateResponse(
        CaseUpdateDetails caseUpdateDetails,
        List<String> warnings) {
        this.caseUpdateDetails = caseUpdateDetails;
        this.warnings = warnings;
    }
}