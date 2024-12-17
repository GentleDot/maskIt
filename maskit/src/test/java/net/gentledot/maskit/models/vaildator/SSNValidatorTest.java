package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SSNValidatorTest {
    private final SSNValidator ssnValidator = new SSNValidator();

    @Test
    void testisValidWhenInputIsValidThenReturnTrue() {
        String ssn = "900101-1234567";
        boolean result = ssnValidator.isValid(ssn);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsValidWithDifferentCenturyThenReturnTrue() {
        String ssn = "000101-4234567";
        boolean result = ssnValidator.isValid(ssn);
        assertTrue(result);
    }

    @Test
    void falseTest_isValidWhenInputIsTooShortThenReturnFalse() {
        String ssn = "900101-123456";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputIsTooLongThenReturnFalse() {
        String ssn = "900101-12345678";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsLettersThenReturnFalse() {
        String ssn = "900101-1234abc";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsSpecialCharactersThenReturnFalse() {
        String ssn = "900101-1234@67";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsSpacesThenReturnFalse() {
        String ssn = "900101 1234567";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidMonthThenReturnFalse() {
        String ssn = "901301-1234567";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidDayThenReturnFalse() {
        String ssn = "900132-1234567";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidSerialThenReturnFalse() {
        String ssn = "900101-5234567";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void testisValidWhenInputIsEmptyThenReturnFalse() {
        String ssn = "";
        boolean result = ssnValidator.isValid(ssn);
        assertFalse(result);
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> ssnValidator.isValid(null));
    }

}