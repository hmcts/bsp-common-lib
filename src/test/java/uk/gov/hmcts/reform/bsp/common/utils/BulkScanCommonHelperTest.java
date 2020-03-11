package uk.gov.hmcts.reform.bsp.common.utils;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.hmcts.reform.bsp.common.error.FormFieldValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.rules.ExpectedException.none;

public class BulkScanCommonHelperTest {

    private static final String DATE_FIELD_NAME = "DateFieldName";

    @Rule
    public ExpectedException expectedException = none();

    @Test
    public void shouldTransformDateWithRightLeapYearDate() {
        LocalDate date = BulkScanCommonHelper.transformFormDateIntoLocalDate(DATE_FIELD_NAME, "29/02/2020");

        assertThat(date.getDayOfMonth(), CoreMatchers.is(29));
        assertThat(date.getMonth(), CoreMatchers.is(FEBRUARY));
        assertThat(date.getYear(), CoreMatchers.is(2020));
    }

    @Test
    public void shouldFailDateTransformationWithWrongLeapYearDate() {
        expectedException.expect(FormFieldValidationException.class);
        expectedException.expectMessage("DateFieldName must be a valid date");

        BulkScanCommonHelper.transformFormDateIntoLocalDate(DATE_FIELD_NAME, "29/02/2019");
    }

    @Test
    public void getCorrectListOfCommaSeparatedValuesFromOcrFieldsMap() {
        List<String> convertedListOfValues =
            BulkScanCommonHelper.getCommaSeparatedValuesFromOcrDataField("domesticViolence, urgency, previousMIAMattendance, other");

        assertThat(convertedListOfValues, CoreMatchers.allOf(
            hasSize(4),
            CoreMatchers.equalTo(Arrays.asList("domesticViolence", "urgency", "previousMIAMattendance", "other")
            )));
    }

    @Test
    public void getEmptyListOfCommaSeparatedValuesFromEmptyString() {
        List<String> convertedListOfValues =
            BulkScanCommonHelper.getCommaSeparatedValuesFromOcrDataField("");

        assertThat(convertedListOfValues, CoreMatchers.is(empty()));
    }

    @Test
    public void getListOfCommaSeparatedValuesFromStringWithBlankSpaces() {
        List<String> convertedListOfValues =
            BulkScanCommonHelper.getCommaSeparatedValuesFromOcrDataField("    ,     , blah,   , string with spaces,  wat  ");

        assertThat(convertedListOfValues, CoreMatchers.allOf(
            hasSize(3),
            CoreMatchers.equalTo(Arrays.asList("blah", "string with spaces", "wat")
            )));

    }
}
