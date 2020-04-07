package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uk.gov.hmcts.reform.bsp.common.mapper.GenericMapper.addMappingsTo;

public class AddressMapper {

    public static void applyMappings(
        String prefix,
        String parentField,
        List<OcrDataField> ocrDataFields,
        Map<String, Object> modifiedMap) {
        addMappingsTo(parentField, getAddressMapping(prefix), modifiedMap, ocrDataFields);
    }

    /**
     * All types of Address complex types (such as predefined in CCD: AddressUK, AddressGlobalUK and AddressGlobal
     * and types defined in Divorce) do have address line 2.
     *
     * <p>More: https://tools.hmcts.net/confluence/display/RCCD/Address+global+complex+type
     * */
    private static ImmutableMap<String, String> getAddressMapping(String prefix) {
        String nestedFieldPrefix = StringUtils.capitalize(prefix + "Address");
        Map<String, String> address = new HashMap<>();

        address.put(nestedFieldPrefix + "Line1", "AddressLine1");
        address.put(nestedFieldPrefix + "Line2", "AddressLine2");
        address.put(nestedFieldPrefix + "County", "County");
        address.put(nestedFieldPrefix + "Postcode", "PostCode");
        address.put(nestedFieldPrefix + "Town", "PostTown");
        address.put(nestedFieldPrefix + "Country", "Country");

        return ImmutableMap.copyOf(address);
    }
}
