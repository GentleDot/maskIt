package net.gentledot.maskit.models.vaildator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SSNValidatorTest {
    private final SSNValidator ssnValidator = new SSNValidator();

    @Test
    void testIsValidSSNWhenInputIsValidThenReturnTrue() {
        String ssn = "900101-1234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertTrue(result);
    }

    @Test
    void testIsValidSSNWhenInputIsValidWithDifferentCenturyThenReturnTrue() {
        String ssn = "000101-4234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertTrue(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputIsTooShortThenReturnFalse() {
        String ssn = "900101-123456";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputIsTooLongThenReturnFalse() {
        String ssn = "900101-12345678";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsLettersThenReturnFalse() {
        String ssn = "900101-1234abc";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsSpecialCharactersThenReturnFalse() {
        String ssn = "900101-1234@67";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsSpacesThenReturnFalse() {
        String ssn = "900101 1234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsInvalidMonthThenReturnFalse() {
        String ssn = "901301-1234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsInvalidDayThenReturnFalse() {
        String ssn = "900132-1234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidSSNWhenInputContainsInvalidSerialThenReturnFalse() {
        String ssn = "900101-5234567";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void testIsValidSSNWhenInputIsNullThenReturnFalse() {
        String ssn = null;
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

    @Test
    void testIsValidSSNWhenInputIsEmptyThenReturnFalse() {
        String ssn = "";
        boolean result = ssnValidator.isValidSSN(ssn);
        assertFalse(result);
    }

}