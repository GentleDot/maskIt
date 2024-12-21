package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {
    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    void testFalse_InvalidEmailWithIPAddressInBrackets() {
        String email = "user@[192.168.1.1]";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_SingleCharacterTLD() {
        String email = "user@domain.c";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_MissingAtAndDomain() {
        String email = "userdomain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_WhenEmpty() {
        String email = "";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_DoubleDotInDomain2() {
        String email = "user@domain..com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_DoubleDotInDomain3() {
        String email = "user@domain...com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_DoubleDotInDomain4() {
        String email = "user@domain....com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDashInDomain2() {
        String email = "user@-domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDashInDomain3() {
        String email = "user@--domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDotInDomain2() {
        String email = "user@.domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDotInDomain3() {
        String email = "user@..domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDotInDomain4() {
        String email = "user@...domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_MissingTLD() {
        String email = "user@domain";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_DoubleDotInDomain() {
        String email = "user@domain..com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_ValidEmailWithIPAddress() {
        String email = "user@192.168.1.1";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_TrailingDashInDomain2() {
        String email = "user@domain-.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_TrailingDotInDomain2() {
        String email = "user@domain.com.";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDashInDomain() {
        String email = "user@-domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_LeadingDotInDomain() {
        String email = "user@.domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_TrailingDashInDomain() {
        String email = "user@domain-.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_TLDTooLong() {
        String email = "user@domain.abcdefghtldtoolong";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_ExclamationMarkInDomain() {
        String email = "user@domain!.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_TrailingDotInDomain() {
        String email = "user@domain.com.";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_MissingUsername() {
        String email = "@domain.com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    @Test
    void testFalse_UnderscoreInDomain() {
        String email = "user@domain_com";
        Assertions.assertThrows(MaskingServiceException.class, () -> emailValidator.isValid(email));
    }

    // Valid email tests
    @Test
    void validEmail() {
        String email = "user@domain.com";
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    void validEmailWithSubdomain() {
        String email = "user@mail.domain.com";
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    void validEmailWithPlus() {
        String email = "user+label@domain.com";
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    void validEmailWithHyphen() {
        String email = "user-name@domain.com";
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    void validEmailWithDotInUsername() {
        String email = "user.name@domain.com";
        assertTrue(emailValidator.isValid(email));
    }
}