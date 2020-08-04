package uk.gov.hmcts.reform.bsp.common.model.document;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CtscContactDetailsTest {

    private static final String CTSC_CENTRE_NAME = "HMCTS Digital Divorce";
    private static final String CTSC_CARE_OF = "c/o " + CTSC_CENTRE_NAME;
    private static final String CTSC_EMAIL_ADDRESS = "divorcecase@justice.gov.uk";
    private static final String CTSC_OPENING_HOURS = "8:00 - 20:00";
    private static final String CTSC_PHONE_NUMBER = "0300 303 0642";
    private static final String CTSC_POSTCODE = "CM20 9QT";
    private static final String CTSC_PO_BOX = "PO Box 12706";
    private static final String CTSC_SERVICE_CENTRE = "Courts and Tribunals Service Centre";
    private static final String CTSC_TOWN = "Harlow";

    @Test
    public void checkAllStatusValues() {

        CtscContactDetails ctscContactDetails = CtscContactDetails.builder()
            .serviceCentre(CTSC_SERVICE_CENTRE)
            .careOf(CTSC_CARE_OF)
            .centreName(CTSC_CENTRE_NAME)
            .poBox(CTSC_PO_BOX)
            .town(CTSC_TOWN)
            .postcode(CTSC_POSTCODE)
            .emailAddress(CTSC_EMAIL_ADDRESS)
            .phoneNumber(CTSC_PHONE_NUMBER)
            .openingHours(CTSC_OPENING_HOURS)
            .build();

        assertEquals(CTSC_SERVICE_CENTRE, ctscContactDetails.getServiceCentre());
        assertEquals(CTSC_CARE_OF, ctscContactDetails.getCareOf());
        assertEquals(CTSC_CENTRE_NAME, ctscContactDetails.getCentreName());
        assertEquals(CTSC_PO_BOX, ctscContactDetails.getPoBox());
        assertEquals(CTSC_TOWN, ctscContactDetails.getTown());
        assertEquals(CTSC_POSTCODE, ctscContactDetails.getPostcode());
        assertEquals(CTSC_EMAIL_ADDRESS, ctscContactDetails.getEmailAddress());
        assertEquals(CTSC_PHONE_NUMBER, ctscContactDetails.getPhoneNumber());
        assertEquals(CTSC_OPENING_HOURS, ctscContactDetails.getOpeningHours());
    }
}
