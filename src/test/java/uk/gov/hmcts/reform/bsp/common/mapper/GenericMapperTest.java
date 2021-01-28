package uk.gov.hmcts.reform.bsp.common.mapper;

import com.google.common.collect.ImmutableMap;
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
import static uk.gov.hmcts.reform.bsp.common.mapper.GenericMapper.addMappingsTo;
import static uk.gov.hmcts.reform.bsp.common.mapper.GenericMapper.getValueFromOcrDataFields;

public class GenericMapperTest {

    @Test
    public void getValueFromOcrDataFieldsGetsEmptyWhenEmptyList() {
        List<OcrDataField> input = Collections.emptyList();

        assertThat(getValueFromOcrDataFields("myField", input), is(Optional.empty()));
    }

    @Test
    public void getValueFromOcrDataFieldsGetsEmptyWhenValueOfTheFoundElementIsNull() {
        List<OcrDataField> input = asList(new OcrDataField("myField", null));

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

        Optional<String> result = getValueFromOcrDataFields("myField1", input);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is("this should be found"));
    }

    @Test
    public void addMappingsToShouldMapOcrFieldsIntoCcdFields() {
        List<OcrDataField> input = asList(
            new OcrDataField("OCR_ParentObjectField1", "ok"),
            new OcrDataField("OCR_ParentObjectField2", "fine"),
            new OcrDataField("OCR_dontUseMe", "I am useless")
        );

        Map<String, Object> caseData = new HashMap<>();

        addMappingsTo(
            "extractedObject",
            ImmutableMap.of(
                "OCR_ParentObjectField1", "parentObjectField1",
                "OCR_ParentObjectField2", "parentObjectField2"
            ),
            caseData,
            input
        );

        Map<String, Object> result = (Map) caseData.get("extractedObject");

        assertThat(result.size(), is(2));
        assertThat(result.get("parentObjectField1"), is("ok"));
        assertThat(result.get("parentObjectField2"), is("fine"));
    }

    @Test
    public void addMappingsToShouldMapTheLastDefinedValueWhenCcdFieldIsRedefined() {
        List<OcrDataField> input = asList(
            new OcrDataField("OCR_ParentObjectField1", "ok"),
            new OcrDataField("OCR_ParentObjectField2", "fine")
        );

        Map<String, Object> caseData = new HashMap<>();

        addMappingsTo(
            "extractedObject",
            ImmutableMap.of(
                "OCR_ParentObjectField1", "FieldDefinedTwice",
                "OCR_ParentObjectField2", "FieldDefinedTwice"
            ),
            caseData,
            input
        );

        Map<String, Object> result = (Map) caseData.get("extractedObject");

        assertThat(result.size(), is(1));
        assertThat(result.get("FieldDefinedTwice"), is("fine"));
    }

    @Test
    public void addMappingsToShouldMapOnly1FieldWhenKeysAreDuplicated() {
        List<OcrDataField> input = asList(
            new OcrDataField("OCR_ParentObjectField1", "ok"),
            new OcrDataField("OCR_ParentObjectField1", "fine")
        );

        Map<String, Object> caseData = new HashMap<>();

        addMappingsTo(
            "extractedObject",
            ImmutableMap.of(
                "OCR_ParentObjectField1", "parentObjectField1",
                "OCR_ParentObjectField2", "parentObjectField2"
            ),
            caseData,
            input
        );

        Map<String, Object> result = (Map) caseData.get("extractedObject");

        assertThat(result.size(), is(1));
        assertThat(result.get("parentObjectField1"), is("ok"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMappingsToShouldThrowIllegalArgumentExceptionWhenOcrFieldKeyDuplicatedInMap() {
        List<OcrDataField> input = asList(
            new OcrDataField("OCR_ParentObjectField1", "ok")
        );

        addMappingsTo(
            "extractedObject",
            ImmutableMap.of(
                "OCR_ParentObjectField1", "parentObjectField1",
                "OCR_ParentObjectField1", "parentObjectField2"
            ),
            new HashMap<>(),
            input
        );
    }
}
