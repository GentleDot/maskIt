package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void falseTest_isValidWhenInputIsTooShortThenThrowException() {
        String phoneNumber = "010123";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputIsTooLongThenThrowException() {
        String phoneNumber = "010123456789012";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsLettersThenThrowException() {
        String phoneNumber = "0101234abcd";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsSpecialCharactersThenThrowException() {
        String phoneNumber = "010-1234-567@";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsSpacesThenThrowException() {
        String phoneNumber = "010 1234 5678";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsMultipleDashesThenThrowException() {
        String phoneNumber = "010--1234--5678";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsLeadingPlusWithoutCountryCodeThenThrowException() {
        String phoneNumber = "+1012345678";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidCountryCodeThenThrowException() {
        String phoneNumber = "+991012345678";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void testisValidWhenInputIsEmptyThenThrowException() {
        String phoneNumber = "";
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(phoneNumber));
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> phoneNumberValidator.isValid(null));
    }
}