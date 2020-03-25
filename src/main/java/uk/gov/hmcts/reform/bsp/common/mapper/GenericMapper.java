package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericMapper {

    public static void addMappingsTo(String parentField, ImmutableMap<String, String> mappings,
                                     Map<String, Object> modifiedMap, List<OcrDataField> ocrDataFields) {
        HashMap<String, Object> parentFieldObject = new HashMap<>();

        mappings.forEach((srcField, targetField) -> {
            mapIfSourceExists(srcField, targetField, parentFieldObject, ocrDataFields);
        });

        if (parentFieldObject.size() > 0) {
            modifiedMap.put(parentField, parentFieldObject);
        }
    }

    public static Optional<String> getValueFromOcrDataFields(String fieldName, List<OcrDataField> ocrDataFields) {
        return ocrDataFields.stream()
            .filter(f -> f.getName().equals(fieldName) && f.getValue() != null)
            .map(OcrDataField::getValue)
            .findFirst();
    }

    private static void mapIfSourceExists(
        String srcField, String targetField, HashMap<String, Object> parent, List<OcrDataField> ocrDataFields) {
        getValueFromOcrDataFields(srcField, ocrDataFields)
            .ifPresent(srcFieldValue -> {
                parent.put(targetField, srcFieldValue);
            });
    }
}

