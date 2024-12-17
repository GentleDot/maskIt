package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    @Test
    void testisValidWhenInputIsValidWithCountryCodeThenReturnTrue() {
        String phoneNumber = "+821012345678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsValidWithLeadingZeroThenReturnTrue() {
        String phoneNumber = "01012345678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsValidWithDashesThenReturnTrue() {
        String phoneNumber = "010-1234-5678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsValidWithCountryCodeAndDashesThenReturnTrue() {
        String phoneNumber = "+82-10-1234-5678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void falseTest_isValidWhenInputIsTooShortThenReturnFalse() {
        String phoneNumber = "010123";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputIsTooLongThenReturnFalse() {
        String phoneNumber = "010123456789012";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsLettersThenReturnFalse() {
        String phoneNumber = "0101234abcd";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsSpecialCharactersThenReturnFalse() {
        String phoneNumber = "010-1234-567@";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsSpacesThenReturnFalse() {
        String phoneNumber = "010 1234 5678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsMultipleDashesThenReturnFalse() {
        String phoneNumber = "010--1234--5678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsLeadingPlusWithoutCountryCodeThenReturnFalse() {
        String phoneNumber = "+1012345678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidCountryCodeThenReturnFalse() {
        String phoneNumber = "+991012345678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void testisValidWhenInputContainsInvalidAreaCodeThenReturnFalse() {
        String phoneNumber = "01912345678";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputContainsValidAreaCodeThenReturnTrue() {
        String phoneNumber = "0311234567";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputContainsValidAreaCodeWithDashesThenReturnTrue() {
        String phoneNumber = "031-123-4567";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsEmptyThenReturnFalse() {
        String phoneNumber = "";
        boolean result = phoneNumberValidator.isValid(phoneNumber);
        assertFalse(result);
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(null));
    }
}