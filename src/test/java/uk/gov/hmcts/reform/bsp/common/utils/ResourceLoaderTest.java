package uk.gov.hmcts.reform.bsp.common.utils;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.error.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ResourceLoaderTest {

    @Test
    public void shouldLoadFile() throws Exception {
        String data = ResourceLoader.loadResourceAsString("fixtures/example.json");
        assertThat(data, CoreMatchers.is("{}"));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowException() throws Exception {
        ResourceLoader.loadResourceAsString("I can't find this file");
    }
}
