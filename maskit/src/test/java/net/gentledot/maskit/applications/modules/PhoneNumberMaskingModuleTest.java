package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberMaskingModuleTest {
    private final PhoneNumberMaskingModule phoneNumberMaskingModule = PhoneNumberMaskingModule.newInstance();

    @Test
    void testMask() {
        String phoneNumber = "01012345678";
        String expected = "*******5678";
        String result = phoneNumberMaskingModule.mask(phoneNumber);
        assertEquals(expected, result);
    }

    @Test
    void testMaskWithHyphen() {
        String phoneNumber = "010-1234-5678";
        String expected = "***-****-5678";
        String result = phoneNumberMaskingModule.mask(phoneNumber);
        assertEquals(expected, result);
    }

    @Test
    void testMaskShortPhoneNumberWithHyphen() {
        String phoneNumber = "02-123-2345";
        String expected = "**-***-2345";
        String result = phoneNumberMaskingModule.mask(phoneNumber);
        assertEquals(expected, result);
    }

    @Test
    void testMaskInvalidPhoneNumber() {
        String invalidPhoneNumber = "123";
        assertThrows(MaskingServiceException.class, () -> phoneNumberMaskingModule.mask(invalidPhoneNumber));
    }

    @Test
    void testMaskIndex() {
        String data = "01012345678";
        String expected = "01****45678";
        String result = phoneNumberMaskingModule.mask(data, 2, 5);
        assertEquals(expected, result);
    }

    @Test
    void testMaskFront() {
        String data = "01012345678";
        String expected = "****2345678";
        String result = phoneNumberMaskingModule.maskFront(data, 4);
        assertEquals(expected, result);
    }

    @Test
    void testMaskBack() {
        String data = "01012345678";
        String expected = "0101234****";
        String result = phoneNumberMaskingModule.maskBack(data, 4);
        assertEquals(expected, result);
    }

    @Test
    void testMaskWithRegex() {
        String data = "01012345678";
        Pattern regex = Pattern.compile("^\\d{3}");
        String expected = "***12345678";
        String result = phoneNumberMaskingModule.maskWithRegex(data, regex);
        assertEquals(expected, result);
    }

}
