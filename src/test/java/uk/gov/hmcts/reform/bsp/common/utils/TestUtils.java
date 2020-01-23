package uk.gov.hmcts.reform.bsp.common.utils;

import org.hamcrest.Matcher;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;

public class TestUtils {

    private TestUtils() {
        // no-op
    }

    public static Matcher<List<?>> isEmptyList() {
        return is(emptyList());
    }
}
