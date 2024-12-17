package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private EmailValidator emailValidator = new EmailValidator();

    @Test
    void testValidEmail() {
        assertTrue(emailValidator.isValid("test@example.com"));
    }

    @Test
    void testValidEmailWithPlusAndDots() {
        assertTrue(emailValidator.isValid("user.name+tag+sorting@example.com"));
    }

    @Test
    void testValidEmailWithSubdomain() {
        assertTrue(emailValidator.isValid("user.name@example.co.uk"));
    }

    @Test
    void testValidEmailWithUnderscore() {
        assertTrue(emailValidator.isValid("user_name@example.com"));
    }

    @Test
    void testValidEmailWithSubdomain2() {
        assertTrue(emailValidator.isValid("username@sub.example.com"));
    }

    @Test
    void testFalse_ValidEmailWithIPAddress() {
        assertFalse(emailValidator.isValid("username@123.123.123.123"));
    }

    @Test
    void testFalse_InvalidEmailWithIPAddressInBrackets() {
        assertFalse(emailValidator.isValid("username@[123.123.123.123]"));
    }

    @Test
    void testFalse_MissingAtAndDomain() {
        assertFalse(emailValidator.isValid("plainaddress"));
    }

    @Test
    void testFalse_MissingUsername() {
        assertFalse(emailValidator.isValid("@example.com"));
    }

    @Test
    void testFalse_LeadingDotInDomain() {
        assertFalse(emailValidator.isValid("username@.com"));
    }

    @Test
    void testFalse_LeadingDotInDomain2() {
        assertFalse(emailValidator.isValid("username@.com.com"));
    }

    @Test
    void testFalse_LeadingDashInDomain() {
        assertFalse(emailValidator.isValid("username@-example.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain() {
        assertFalse(emailValidator.isValid("username@example..com"));
    }

    @Test
    void testFalse_LeadingDotInDomain3() {
        assertFalse(emailValidator.isValid("username@.example.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain2() {
        assertFalse(emailValidator.isValid("username@.example..com"));
    }

    @Test
    void testFalse_TrailingDotInDomain() {
        assertFalse(emailValidator.isValid("username@.example.com."));
    }

    @Test
    void testFalse_LeadingDashInDomain2() {
        assertFalse(emailValidator.isValid("username@-example.com"));
    }

    @Test
    void testFalse_UnderscoreInDomain() {
        assertFalse(emailValidator.isValid("username@exam_ple.com"));
    }

    @Test
    void testFalse_ExclamationMarkInDomain() {
        assertFalse(emailValidator.isValid("username@exam!ple.com"));
    }

    @Test
    void testFalse_SingleCharacterTLD() {
        assertFalse(emailValidator.isValid("username@example.c"));
    }

    @Test
    void testFalse_TLDTooLong() {
        assertFalse(emailValidator.isValid("username@example..thisisaverylongtldthatexceedtld"));
    }

    @Test
    void testFalse_LeadingDotInDomain4() {
        assertFalse(emailValidator.isValid("username@.com"));
    }

    @Test
    void testFalse_MissingTLD() {
        assertFalse(emailValidator.isValid("username@com"));
    }

    @Test
    void testFalse_LeadingDashInDomain3() {
        assertFalse(emailValidator.isValid("username@-com.com"));
    }

    @Test
    void testFalse_TrailingDashInDomain() {
        assertFalse(emailValidator.isValid("username@com-.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain3() {
        assertFalse(emailValidator.isValid("username@com..com"));
    }

    @Test
    void testFalse_TrailingDashInDomain2() {
        assertFalse(emailValidator.isValid("username@com.com-"));
    }

    @Test
    void testFalse_TrailingDotInDomain2() {
        assertFalse(emailValidator.isValid("username@com.com."));
    }

    @Test
    void testFalse_DoubleDotInDomain4() {
        assertFalse(emailValidator.isValid("username@com.com.."));
    }

    @Test
    void testisValidWhenEmptyThenFalse() {
        String emptyEmail = "";
        assertFalse(emailValidator.isValid(emptyEmail), "Expected empty email to return false");
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(null));
    }
}