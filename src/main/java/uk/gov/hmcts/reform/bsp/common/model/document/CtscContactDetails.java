package uk.gov.hmcts.reform.bsp.common.model.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CtscContactDetails {
    @JsonProperty("serviceCentre")
    private String serviceCentre;

    @JsonProperty("careOf")
    private String careOf;

    @JsonProperty("centreName")
    private String centreName;

    @JsonProperty("poBox")
    private String poBox;

    @JsonProperty("town")
    private String town;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("openingHours")
    private String openingHours;
}
