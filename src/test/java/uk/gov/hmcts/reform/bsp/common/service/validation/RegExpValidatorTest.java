package uk.gov.hmcts.reform.bsp.common.service.validation;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.model.validation.BulkScanValidationPatterns.CCD_EMAIL_REGEX;
import static uk.gov.hmcts.reform.bsp.common.utils.TestUtils.isEmptyList;

public class RegExpValidatorTest {

    @Test
    public void shouldReturnEmptyListOfValidationErrorsWhenFieldIsValid() {
        Map<String, String> input = buildMap("email", "valid@email.com");

        assertThat(RegExpValidator.validateField(input, "email", CCD_EMAIL_REGEX), isEmptyList());
    }

    @Test
    public void shouldReturnEmptyListOfValidationErrorsWhenFieldDoesntExist() {
        Map<String, String> input = buildMap("email", "valid@email.com");

        assertThat(RegExpValidator.validateField(input, "you can't find me", CCD_EMAIL_REGEX), isEmptyList());
    }

    @Test
    public void shouldReturnListOfValidationErrorsWhenRegExpDoesntMatch() {
        Map<String, String> input = buildMap("email", "this is invalid email");

        assertThat(RegExpValidator.validateField(input, "email", CCD_EMAIL_REGEX).get(0), is("email is not in a valid format"));
    }

    private static ImmutableMap<String, String> buildMap(String key, String value) {
        return ImmutableMap.of(key, value);
    }
}
