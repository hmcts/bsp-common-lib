package uk.gov.hmcts.reform.bsp.common.model.shared.in;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]'Z'")
    private LocalDateTime scannedDate;

    @JsonProperty("delivery_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]'Z'")
    private LocalDateTime deliveryDate;

}