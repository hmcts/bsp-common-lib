package uk.gov.hmcts.reform.bsp.common.model.shared.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InputScannedDoc {

    @JsonProperty("type")
    private String type;

    @JsonProperty("subtype")
    private String subtype;

    @JsonProperty("url")
    private InputScannedDocUrl document;

    @JsonProperty("control_number")
    private String controlNumber;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("scanned_date")
    private LocalDateTime scannedDate;

    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;

}