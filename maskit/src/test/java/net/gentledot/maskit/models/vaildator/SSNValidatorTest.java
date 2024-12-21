package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @ParameterizedTest
    @ValueSource(strings = {
            "900101-123456",
            "900101-12345678",
            "900101-1234abc",
            "900101-1234@67",
            "900101 1234567",
            "901301-1234567",
            "900132-1234567",
            "900101-5234567",
            ""
    })
    void testInvalidSSNInputs(String ssn) {
        assertThrows(MaskingServiceException.class, () -> ssnValidator.isValid(ssn));
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> ssnValidator.isValid(null));
    }
}