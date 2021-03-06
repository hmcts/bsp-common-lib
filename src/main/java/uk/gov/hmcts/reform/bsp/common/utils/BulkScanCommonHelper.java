package uk.gov.hmcts.reform.bsp.common.utils;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import uk.gov.hmcts.reform.bsp.common.error.FormFieldValidationException;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.time.format.ResolverStyle.STRICT;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class BulkScanCommonHelper {

    private static final DateTimeFormatter EXPECTED_DATE_FORMAT_FROM_FORM = DateTimeFormatter
        .ofPattern("dd/MM/uuuu")
        .withResolverStyle(STRICT);

    private static final DateTimeFormatter CCD_DATE_FORMAT = DateTimeFormatter
        .ofPattern("uuuu-MM-dd")
        .withResolverStyle(STRICT);

    private static final Pattern COMMA_AND_WHITESPACE = Pattern.compile(",\\s*");

    /**
     * Strips out all fields with empty or null values and returns map of remaining fields.
     *
     * @param ocrDataFields List of ocrDataFields
     * @return a map with only the fields that were not blank from the OCR data.
     */
    public static Map<String, String> produceMapWithoutEmptyEntries(List<OcrDataField> ocrDataFields) {
        return ocrDataFields.stream()
            .filter(field -> isNotBlank(field.getValue()))
            .collect(toMap(OcrDataField::getName, OcrDataField::getValue));
    }

    public static LocalDate transformFormDateIntoLocalDate(String formFieldName, String formDate) throws FormFieldValidationException {
        try {
            return LocalDate.parse(formDate, EXPECTED_DATE_FORMAT_FROM_FORM);
        } catch (DateTimeParseException exception) {
            throw new FormFieldValidationException(String.format("%s must be a valid date", formFieldName));
        }
    }

    public static String transformFormDateIntoCcdDate(String formFieldName, String formDate) {
        return transformFormDateIntoLocalDate(formFieldName, formDate).format(CCD_DATE_FORMAT);
    }

    /**
     * Validates date from form and will return a validation error is date is not in correct format.
     *
     * @param fieldsMap map of OCR Data fields from which value will be chosen
     * @param fieldName date field to be formatted
     * @return an optional validation message.
     */
    public static Optional<String> validateFormDate(Map<String, String> fieldsMap, String fieldName) {
        Optional<String> validationMessage = Optional.empty();

        try {
            Optional.ofNullable(fieldsMap.get(fieldName))
                .ifPresent(formDate -> transformFormDateIntoLocalDate(fieldName, formDate));
        } catch (FormFieldValidationException exception) {
            validationMessage = Optional.of(exception.getMessage());
        }

        return validationMessage;
    }

    /**
     * The following assumptions are in place.
     * - the delimiter is a comma possibly followed by a number of whitespace characters: ",\\s*" regex
     * - leading and trailing white spaces for each entry are removed - consequentially an empty entry will be discarded
     * so a warning will not be raised on further processing
     *
     * @param commaSeparatedString the comma separated string containing entries to be parsed
     * @return a list of strings without empty values
     */
    public static List<String> getCommaSeparatedValuesFromOcrDataField(String commaSeparatedString) {
        if (commaSeparatedString.isEmpty()) {
            return Collections.emptyList();
        }
        return Splitter.on(COMMA_AND_WHITESPACE).splitToList(commaSeparatedString)
            .stream()
            .filter(Objects::nonNull)
            .map(String::trim)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());
    }
}