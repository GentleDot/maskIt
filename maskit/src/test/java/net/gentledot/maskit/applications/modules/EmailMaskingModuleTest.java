package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailMaskingModuleTest {
    private EmailMaskingModule emailMaskingModule = EmailMaskingModule.newInstance();

    @CsvSource({
            "user@example.com, ****@*******.com",
            "user.name+tag+sorting@example.com, *********************@*******.com",
            "user@sub.example.com, ****@***********.com",
            "user@localserver.com, ****@***********.com",
            "user@domain-with-dash.com, ****@****************.com",
            "test@example.com, ****@*******.com",
            "user.name+tag+sorting@example.com, *********************@*******.com",
            "user.name@example.co.uk, *********@**********.uk",
            "user_name@example.com, *********@*******.com",
            "username@sub.example.com, ********@***********.com"
    })
    @ParameterizedTest
    void testMaskEmail(String data, String expected) {
        String masked = emailMaskingModule.mask(data);
        assertEquals(expected, masked);
    }

    @ParameterizedTest
    @CsvSource({
            "invalid-email",
            "user@.com",
            "user@com",
            "@example.com",
            "username@123.123.123.123",
            "username@[123.123.123.123]",
            "plainaddress",
            "@example.com",
            "username@.com",
            "username@.com.com",
            "username@-example.com",
            "username@example..com",
            "username@.example.com",
            "username@.example..com",
            "username@.example.com.",
            "username@-example.com",
            "username@exam_ple.com",
            "username@exam!ple.com",
            "username@example.c",
            "username@example..thisisaverylongtldthatexceedtld",
            "username@.com",
            "username@com",
            "username@-com.com",
            "username@com-.com",
            "username@com..com",
            "username@com.com-",
            "username@com.com."
    })
    void testMaskEmailWhenInvalidEmailThenThrowException(String invalidEmail) {
        assertThrows(MaskingServiceException.class, () -> emailMaskingModule.mask(invalidEmail));
    }
}