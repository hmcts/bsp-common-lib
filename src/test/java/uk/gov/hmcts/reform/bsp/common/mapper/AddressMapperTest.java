package uk.gov.hmcts.reform.bsp.common.mapper;

import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.mapper.AddressMapper.applyMappings;

public class AddressMapperTest {

    public static final String LINE_1 = "102 Petty France";
    public static final String LINE_2 = "6th floor";
    public static final String TOWN = "London";
    public static final String POSTCODE = "SW8 2PX";
    public static final String COUNTY = "Greater London";
    public static final String COUNTRY = "UK";

    @Test
    public void applyMappingsShouldMapAllFieldsWhenTheyAreProvided() {
        List<OcrDataField> input = asList(
            new OcrDataField("MyAddressLine1", LINE_1),
            new OcrDataField("MyAddressLine2", LINE_2),
            new OcrDataField("MyAddressTown", TOWN),
            new OcrDataField("MyAddressPostcode", POSTCODE),
            new OcrDataField("MyAddressCounty", COUNTY),
            new OcrDataField("MyAddressCountry", COUNTRY)
        );

        HashMap<String, Object> caseData = new HashMap<>();

        applyMappings("my", "personalAddress", input, caseData);

        Map address = (Map)caseData.get("personalAddress");

        assertThat(address.size(), is(6));
        assertThat(address.get("AddressLine1"), is(LINE_1));
        assertThat(address.get("AddressLine2"), is(LINE_2));
        assertThat(address.get("PostTown"), is(TOWN));
        assertThat(address.get("PostCode"), is(POSTCODE));
        assertThat(address.get("County"), is(COUNTY));
        assertThat(address.get("Country"), is(COUNTRY));
    }

    @Test
    public void applyMappingsShouldMapOnlyProvided() {
        List<OcrDataField> input = asList(
            new OcrDataField("MyAddressLine1", LINE_1),
            new OcrDataField("MyAddressCountry", COUNTRY)
        );

        HashMap<String, Object> caseData = new HashMap<>();

        applyMappings("my", "personalAddress", input, caseData);

        Map address = (Map)caseData.get("personalAddress");

        assertThat(address.size(), is(2));
        assertThat(address.get("AddressLine1"), is(LINE_1));
        assertThat(address.get("Country"), is(COUNTRY));
    }

    @Test
    public void applyMappingsShouldMapOnlyNonNullValues() {
        List<OcrDataField> input = asList(
            new OcrDataField("MyAddressLine1", LINE_1),
            new OcrDataField("MyAddressLine2", null),
            new OcrDataField("MyAddressPostcode", ""),
            new OcrDataField("MyAddressTown", " "),
            new OcrDataField("MyAddressCountry", COUNTRY)
        );

        HashMap<String, Object> caseData = new HashMap<>();

        applyMappings("my", "personalAddress", input, caseData);

        Map<String, String> address = (Map)caseData.get("personalAddress");

        assertThat(address.size(), is(4));
        assertThat(address.get("AddressLine1"), is(LINE_1));
        assertThat(address.get("Country"), is(COUNTRY));
        assertThat(address.get("PostTown"), is(" "));
        assertThat(address.get("PostCode"), is(""));
    }
}
