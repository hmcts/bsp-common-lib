package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddressMapper {

    public static void applyMappings(
            String prefix,
            String parentField,
            List<OcrDataField> ocrDataFields,
            Map<String, Object> modifiedMap) {

        String nestedFieldPrefix = StringUtils.capitalize(prefix + "Address");

        addMappingsTo(
            parentField,
            ImmutableMap.of(
                nestedFieldPrefix + "Line1", "AddressLine1",
                nestedFieldPrefix + "County", "County",
                nestedFieldPrefix + "Postcode", "PostCode",
                nestedFieldPrefix + "Town", "PostTown",
                nestedFieldPrefix + "Country", "Country"
            ),
            modifiedMap,
            ocrDataFields
        );
    }

    private static void addMappingsTo(String parentField, ImmutableMap<String, String> mappings,
                                     Map<String, Object> modifiedMap, List<OcrDataField> ocrDataFields) {
        HashMap<String, Object> parentFieldObject = new HashMap<>();

        mappings.forEach((srcField, targetField) -> {
            mapIfSourceExists(srcField, targetField, parentFieldObject, ocrDataFields);
        });

        if (parentFieldObject.size() > 0) {
            modifiedMap.put(parentField, parentFieldObject);
        }
    }

    private static void mapIfSourceExists(
            String srcField, String targetField, HashMap<String, Object> parent, List<OcrDataField> ocrDataFields) {
        getValueFromOcrDataFields(srcField, ocrDataFields)
            .ifPresent(srcFieldValue -> {
                parent.put(targetField, srcFieldValue);
            });
    }

    static Optional<String> getValueFromOcrDataFields(String fieldName, List<OcrDataField> ocrDataFields) {
        return ocrDataFields.stream()
            .filter(f -> f.getName().equals(fieldName))
            .map(OcrDataField::getValue)
            .findFirst();
    }
}
