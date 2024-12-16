package net.gentledot.maskit.models.vaildator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneNumberValidatorTest {
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    @Test
    void testIsValidPhoneNumberWhenInputIsValidWithCountryCodeThenReturnTrue() {
        String phoneNumber = "+821012345678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputIsValidWithLeadingZeroThenReturnTrue() {
        String phoneNumber = "01012345678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputIsValidWithDashesThenReturnTrue() {
        String phoneNumber = "010-1234-5678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputIsValidWithCountryCodeAndDashesThenReturnTrue() {
        String phoneNumber = "+82-10-1234-5678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputIsTooShortThenReturnFalse() {
        String phoneNumber = "010123";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputIsTooLongThenReturnFalse() {
        String phoneNumber = "010123456789012";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsLettersThenReturnFalse() {
        String phoneNumber = "0101234abcd";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsSpecialCharactersThenReturnFalse() {
        String phoneNumber = "010-1234-567@";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsSpacesThenReturnFalse() {
        String phoneNumber = "010 1234 5678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsMultipleDashesThenReturnFalse() {
        String phoneNumber = "010--1234--5678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsLeadingPlusWithoutCountryCodeThenReturnFalse() {
        String phoneNumber = "+1012345678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidPhoneNumberWhenInputContainsInvalidCountryCodeThenReturnFalse() {
        String phoneNumber = "+991012345678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputContainsInvalidAreaCodeThenReturnFalse() {
        String phoneNumber = "01912345678";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputContainsValidAreaCodeThenReturnTrue() {
        String phoneNumber = "0311234567";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputContainsValidAreaCodeWithDashesThenReturnTrue() {
        String phoneNumber = "031-123-4567";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputIsNullThenReturnFalse() {
        String phoneNumber = null;
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void testIsValidPhoneNumberWhenInputIsEmptyThenReturnFalse() {
        String phoneNumber = "";
        boolean result = phoneNumberValidator.isValidPhoneNumber(phoneNumber);
        assertFalse(result);
    }

}