package uk.gov.hmcts.reform.bsp.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.hmcts.reform.bsp.common.error.FileNotFoundException;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceLoader {

    public static String loadResourceAsString(final String filePath) throws Exception {
        return new String(loadResource(filePath), StandardCharsets.UTF_8);
    }

    public static <T> T loadJsonToObject(String filePath, Class<T> type) {
        try {
            return jsonToObject(loadResource(filePath), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObject(byte[] json, Class<T> type) {
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] loadResource(final String filePath) throws Exception {
        URL url = ResourceLoader.class.getClassLoader().getResource(filePath);

        if (url == null) {
            throw new FileNotFoundException(String.format("Could not find resource in path %s", filePath));
        }

        return Files.readAllBytes(Paths.get(url.toURI()));
    }
}
