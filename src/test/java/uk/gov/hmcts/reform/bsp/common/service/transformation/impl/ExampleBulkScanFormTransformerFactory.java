package uk.gov.hmcts.reform.bsp.common.service.transformation.impl;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.bsp.common.service.transformation.BulkScanFormTransformerFactory;

import javax.annotation.PostConstruct;

import java.util.HashMap;

@Component
public class ExampleBulkScanFormTransformerFactory extends BulkScanFormTransformerFactory {

    public static final String TRANSFORMER_NAME = "exampleTransformer";

    @PostConstruct
    public void init() {
        bulkScanFormTransformerMap = new HashMap<>();
        bulkScanFormTransformerMap.put(TRANSFORMER_NAME, new ExampleBulkScanFormTransformer());
    }

}