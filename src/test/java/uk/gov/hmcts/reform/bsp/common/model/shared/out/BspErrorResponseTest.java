package uk.gov.hmcts.reform.bsp.common.model.shared.out;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.utils.TestUtils.isEmptyList;

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

}
