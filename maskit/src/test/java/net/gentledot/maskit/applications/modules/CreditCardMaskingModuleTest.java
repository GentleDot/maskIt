package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreditCardMaskingModuleTest {

    private final CreditCardMaskingModule creditCardMaskingModule = CreditCardMaskingModule.newInstance();

    @ParameterizedTest
    @CsvSource({
            // VISA
            "4111111111111111, ************1111",
            "4111-1111-1111-1111, ****-****-****-1111",
            // MasterCard
            "5555555555554444, ************4444",
            "5500-0000-0000-0004, ****-****-****-0004",
            // American Express
            "378282246310005, ***********0005",
            "3400-0000-0000-009, ****-****-****-009",
            // Diners Club
            "30569309025904, **********5904",
            "3000-000000-0004, ****-******-0004",
            // Discover
            "6011111111111117, ************1117",
            "6011-0000-0000-0004, ****-****-****-0004",
            // JCB
            "3530111333300000, ************0000",
            "2131-0000-0000-0008, ****-****-****-0008"
    })
    void testMask(String creditCardNumber, String expectedMaskedNumber) {
        String actualMaskedNumber = creditCardMaskingModule.mask(creditCardNumber);
        assertEquals(expectedMaskedNumber, actualMaskedNumber);
    }

    @Test
    void testMaskWhenInvalidInputThenHandleError() {
        String invalidCreditCardNumber = "123";
        assertThrows(MaskingServiceException.class, () -> creditCardMaskingModule.mask(invalidCreditCardNumber));
    }

    @Test
    void testMaskFrontWhenValidInputThenReturnMaskedFront() {
        String data = "4111111111111111";
        int length = 6;
        String expectedMaskedData = "******1111111111";

        String actualMaskedData = creditCardMaskingModule.maskFront(data, length);

        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @Test
    void testMaskBackWhenValidInputThenReturnMaskedBack() {
        String data = "4111111111111111";
        int length = 6;
        String expectedMaskedData = "4111111111******";

        String actualMaskedData = creditCardMaskingModule.maskBack(data, length);

        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @Test
    void testMaskWithRegexWhenValidInputThenReturnMaskedWithRegex() {
        String data = "4111-1111-1111-1111";
        Pattern regex = Pattern.compile("\\d{4}");
        String expectedMaskedData = "****-****-****-****";

        String actualMaskedData = creditCardMaskingModule.maskWithRegex(data, regex);

        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @Test
    void testMaskWithRegexWhenInvalidInputThenHandleError() {
        String data = "4111-1111-1111-1111";
        Pattern regex = null;

        assertThrows(MaskingServiceException.class, () -> creditCardMaskingModule.maskWithRegex(data, regex));
    }

}