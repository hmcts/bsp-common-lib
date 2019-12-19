package uk.gov.hmcts.reform.bsp.common.model.update.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import uk.gov.hmcts.reform.bsp.common.model.transformation.in.ExceptionRecord;

import java.util.Map;

@Getter
public class BulkScanCaseUpdateRequest {

    private final ExceptionRecord exceptionRecord;
    private final CaseDetails caseDetails;

    public BulkScanCaseUpdateRequest(
        @JsonProperty("exception_record") ExceptionRecord exceptionRecord,
        @JsonProperty("case_details") CaseDetails caseDetails
    ) {
        this.exceptionRecord = exceptionRecord;
        this.caseDetails = caseDetails;
    }
}