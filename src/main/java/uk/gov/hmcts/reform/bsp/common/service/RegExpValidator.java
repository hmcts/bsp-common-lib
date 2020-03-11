package uk.gov.hmcts.reform.bsp.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegExpValidator {

    public static List<String> validateField(Map<String, String> data, String field, String validationRegex) {
        List<String> validationMessages = new ArrayList<>();

        if (data.containsKey(field)) {
            String valueToValidate = data.get(field);
            if (!valueToValidate.matches(validationRegex)) {
                validationMessages.add(field + " is not in a valid format");
            }
        }

        return validationMessages;
    }
}
