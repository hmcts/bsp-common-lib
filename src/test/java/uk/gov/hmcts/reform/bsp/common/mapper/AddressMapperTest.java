package uk.gov.hmcts.reform.bsp.common.mapper;

import org.junit.Test;
import uk.gov.hmcts.reform.bsp.common.model.shared.in.OcrDataField;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.bsp.common.mapper.AddressMapper.applyMappings;
import static uk.gov.hmcts.reform.bsp.common.mapper.AddressMapper.getValueFromOcrDataFields;

public class AddressMapperTest {

    public static final String LINE_1 = "102 Petty France";
    public static final String TOWN = "London";
    public static final String POSTCODE = "SW8 2PX";
    public static final String COUNTY = "Greater London";
    public static final String COUNTRY = "UK";

    @Test
    public void getValueFromOcrDataFieldsGetsEmptyWhenEmptyList() {
        List<OcrDataField> input = Collections.emptyList();

        assertThat(getValueFromOcrDataFields("myField", input), is(Optional.empty()));
    }

    @Test
    public void getValueFromOcrDataFieldsGetsEmptyWhenNoFieldFound() {
        List<OcrDataField> input = asList(new OcrDataField("otherField", "value"));

        assertThat(getValueFromOcrDataFields("myField", input), is(Optional.empty()));
    }

    @Test
    public void getValueFromOcrDataFieldsGetsElementWhenItExistsInListOfOneElement() {
        List<OcrDataField> input = asList(new OcrDataField("myField", "value"));

        assertThat(getValueFromOcrDataFields("myField", input).orElse(""), is("value"));
    }

    @Test
    public void getValueFromOcrDataFieldsGetsFirstElementWhenItExistsInListWithTwoElementsThatMatch() {
        List<OcrDataField> input = asList(
            new OcrDataField("myField", "this should be found"),
            new OcrDataField("myField", "I will not be found")
        );

        Optional<String> result = getValueFromOcrDataFields("myField", input);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is("this should be found"));
    }

    @Test
    public void applyMappingsShouldMapAllFieldsWhenTheyAreProvided() {
        List<OcrDataField> input = asList(
            new OcrDataField("MyAddressLine1", LINE_1),
            new OcrDataField("MyAddressTown", TOWN),
            new OcrDataField("MyAddressPostcode", POSTCODE),
            new OcrDataField("MyAddressCounty", COUNTY),
            new OcrDataField("MyAddressCountry", COUNTRY)
        );

        HashMap<String, Object> caseData = new HashMap<>();

        applyMappings("my", "personalAddress", input, caseData);

        Map address = (Map)caseData.get("personalAddress");

        assertThat(address.size(), is(5));
        assertThat(address.get("AddressLine1"), is(LINE_1));
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
}
