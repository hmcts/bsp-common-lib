package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.List;
import java.util.Map;

import static uk.gov.hmcts.reform.bsp.common.mapper.GenericMapper.addMappingsTo;

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
}
