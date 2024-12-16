package net.gentledot.maskit.models.vaildator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private EmailValidator emailValidator = new EmailValidator();

    @Test
    void testValidEmail() {
        assertTrue(emailValidator.isValidEmail("test@example.com"));
    }

    @Test
    void testValidEmailWithPlusAndDots() {
        assertTrue(emailValidator.isValidEmail("user.name+tag+sorting@example.com"));
    }

    @Test
    void testValidEmailWithSubdomain() {
        assertTrue(emailValidator.isValidEmail("user.name@example.co.uk"));
    }

    @Test
    void testValidEmailWithUnderscore() {
        assertTrue(emailValidator.isValidEmail("user_name@example.com"));
    }

    @Test
    void testValidEmailWithSubdomain2() {
        assertTrue(emailValidator.isValidEmail("username@sub.example.com"));
    }

    @Test
    void testFalse_ValidEmailWithIPAddress() {
        assertFalse(emailValidator.isValidEmail("username@123.123.123.123"));
    }

    @Test
    void testFalse_InvalidEmailWithIPAddressInBrackets() {
        assertFalse(emailValidator.isValidEmail("username@[123.123.123.123]"));
    }

    @Test
    void testFalse_MissingAtAndDomain() {
        assertFalse(emailValidator.isValidEmail("plainaddress"));
    }

    @Test
    void testFalse_MissingUsername() {
        assertFalse(emailValidator.isValidEmail("@example.com"));
    }

    @Test
    void testFalse_LeadingDotInDomain() {
        assertFalse(emailValidator.isValidEmail("username@.com"));
    }

    @Test
    void testFalse_LeadingDotInDomain2() {
        assertFalse(emailValidator.isValidEmail("username@.com.com"));
    }

    @Test
    void testFalse_LeadingDashInDomain() {
        assertFalse(emailValidator.isValidEmail("username@-example.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain() {
        assertFalse(emailValidator.isValidEmail("username@example..com"));
    }

    @Test
    void testFalse_LeadingDotInDomain3() {
        assertFalse(emailValidator.isValidEmail("username@.example.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain2() {
        assertFalse(emailValidator.isValidEmail("username@.example..com"));
    }

    @Test
    void testFalse_TrailingDotInDomain() {
        assertFalse(emailValidator.isValidEmail("username@.example.com."));
    }

    @Test
    void testFalse_LeadingDashInDomain2() {
        assertFalse(emailValidator.isValidEmail("username@-example.com"));
    }

    @Test
    void testFalse_UnderscoreInDomain() {
        assertFalse(emailValidator.isValidEmail("username@exam_ple.com"));
    }

    @Test
    void testFalse_ExclamationMarkInDomain() {
        assertFalse(emailValidator.isValidEmail("username@exam!ple.com"));
    }

    @Test
    void testFalse_SingleCharacterTLD() {
        assertFalse(emailValidator.isValidEmail("username@example.c"));
    }

    @Test
    void testFalse_TLDTooLong() {
        assertFalse(emailValidator.isValidEmail("username@example..thisisaverylongtldthatexceedtld"));
    }

    @Test
    void testFalse_LeadingDotInDomain4() {
        assertFalse(emailValidator.isValidEmail("username@.com"));
    }

    @Test
    void testFalse_MissingTLD() {
        assertFalse(emailValidator.isValidEmail("username@com"));
    }

    @Test
    void testFalse_LeadingDashInDomain3() {
        assertFalse(emailValidator.isValidEmail("username@-com.com"));
    }

    @Test
    void testFalse_TrailingDashInDomain() {
        assertFalse(emailValidator.isValidEmail("username@com-.com"));
    }

    @Test
    void testFalse_DoubleDotInDomain3() {
        assertFalse(emailValidator.isValidEmail("username@com..com"));
    }

    @Test
    void testFalse_TrailingDashInDomain2() {
        assertFalse(emailValidator.isValidEmail("username@com.com-"));
    }

    @Test
    void testFalse_TrailingDotInDomain2() {
        assertFalse(emailValidator.isValidEmail("username@com.com."));
    }

    @Test
    void testFalse_DoubleDotInDomain4() {
        assertFalse(emailValidator.isValidEmail("username@com.com.."));
    }

    @Test
    void testIsValidEmailWhenNullThenFalse() {
        String nullEmail = null;
        assertFalse(emailValidator.isValidEmail(nullEmail), "Expected null email to return false");
    }

    @Test
    void testIsValidEmailWhenEmptyThenFalse() {
        String emptyEmail = "";
        assertFalse(emailValidator.isValidEmail(emptyEmail), "Expected empty email to return false");
    }
}