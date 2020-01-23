package uk.gov.hmcts.reform.bsp.common.errors;

import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.error.InvalidDataException;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.utils.TestUtils.isEmptyList;

public class InvalidDataExceptionTest {

    @Test
    public void shouldHaveEmptyListsOfErrorsAndWarningsWhenNullsGiven() {
        InvalidDataException exception = new InvalidDataException("msg", null, null);

        assertThat(exception.getErrors(), isEmptyList());
        assertThat(exception.getWarnings(), isEmptyList());
        assertThat(exception.getMessage(), is("msg"));
    }

    @Test
    public void shouldHaveEmptyListsOfErrorsAndWarningsWhenEmptyListsGiven() {
        InvalidDataException exception = new InvalidDataException("msg", emptyList(), emptyList());

        assertThat(exception.getErrors(), isEmptyList());
        assertThat(exception.getWarnings(), isEmptyList());
    }

    @Test
    public void shouldHaveListsOfErrorsAndWarningsWhenPopulatedListsGiven() {
        InvalidDataException exception = new InvalidDataException("msg", asList("a"), asList("c"));

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getWarnings().size(), is(1));
    }
}
