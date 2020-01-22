package uk.gov.hmcts.reform.bsp.common.model.shared.out;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BspErrorResponseTest {

    @Test
    public void shouldHaveEmptyErrorsAndEmptyWarningsByDefaultWhenBuilder() {
        BspErrorResponse model = BspErrorResponse.builder().build();

        assertThat(model.getErrors(), isEmptyList());
        assertThat(model.getWarnings(), isEmptyList());
    }

    @Test
    public void shouldHaveEmptyErrorsAndEmptyWarningsByDefaultWhenBuildWithConstructor() {
        BspErrorResponse model = new BspErrorResponse(emptyList(), emptyList());

        assertThat(model.getErrors(), isEmptyList());
        assertThat(model.getWarnings(), isEmptyList());
    }

    @Test
    public void shouldHavePopulatedListOfWarningsAndErrors() {
        BspErrorResponse model = new BspErrorResponse(asList("a", "b"), asList("c"));

        assertThat(model.getErrors().size(), is(2));
        assertThat(model.getWarnings().size(), is(1));
    }

    private static Matcher<List<?>> isEmptyList() {
        return is(emptyList());
    }

}
