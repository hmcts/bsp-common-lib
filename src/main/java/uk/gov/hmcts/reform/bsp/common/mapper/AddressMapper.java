package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uk.gov.hmcts.reform.bsp.common.mapper.GenericMapper.addMappingsTo;

public class AddressMapper {

    public class Field {
        public static final String LINE_1 = "AddressLine1";
        public static final String LINE_2 = "AddressLine2";
        public static final String POSTCODE = "PostCode";
        public static final String TOWN = "PostTown";
        public static final String COUNTY = "County";
        public static final String COUNTRY = "Country";
    }

    public class OcrSuffix {
        public static final String ADDRESS = "Address";
        public static final String LINE_1 = "Line1";
        public static final String LINE_2 = "Line2";
        public static final String TOWN = "Town";
        public static final String POSTCODE = "Postcode";
        public static final String COUNTY = "County";
        public static final String COUNTRY = "Country";
    }

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
        String nestedFieldPrefix = StringUtils.capitalize(prefix + OcrSuffix.ADDRESS);
        Map<String, String> address = new HashMap<>();

        address.put(nestedFieldPrefix + OcrSuffix.LINE_1, Field.LINE_1);
        address.put(nestedFieldPrefix + OcrSuffix.LINE_2, Field.LINE_2);
        address.put(nestedFieldPrefix + OcrSuffix.POSTCODE, Field.POSTCODE);
        address.put(nestedFieldPrefix + OcrSuffix.TOWN, Field.TOWN);
        address.put(nestedFieldPrefix + OcrSuffix.COUNTY, Field.COUNTY);
        address.put(nestedFieldPrefix + OcrSuffix.COUNTRY, Field.COUNTRY);

        return ImmutableMap.copyOf(address);
    }
}
