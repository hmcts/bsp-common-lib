package uk.gov.hmcts.reform.bsp.common.model.shared.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BspErrorResponse {

    @JsonProperty("errors")
    @Builder.Default
    private final List<String> errors = new ArrayList<>();

    @JsonProperty("warnings")
    @Builder.Default
    private final List<String> warnings = new ArrayList<>();
}
