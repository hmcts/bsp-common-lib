package uk.gov.hmcts.reform.bsp.common.utils;

import com.google.common.collect.ImmutableMap;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.error.FileNotFoundException;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResourceLoaderTest {

    public static final String FIXTURES_EXAMPLE_JSON = "fixtures/example.json";

    @Test
    public void shouldLoadFile() throws Exception {
        String data = ResourceLoader.loadResourceAsString(FIXTURES_EXAMPLE_JSON);
        assertThat(data, is("{\"key\":\"value\"}"));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowException() throws Exception {
        ResourceLoader.loadResourceAsString("I can't find this file");
    }

    @Test
    public void shouldLoadFileAsMap() {
        Map<String, String> map = ResourceLoader.loadJsonToObject(FIXTURES_EXAMPLE_JSON, Map.class);
        assertThat(map, is(ImmutableMap.of("key", "value")));
    }
}
