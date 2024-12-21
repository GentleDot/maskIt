package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SSNMaskingModuleTest {
    private final SSNMaskingModule ssnMaskingModule = SSNMaskingModule.newInstance();

    @ParameterizedTest
    @CsvSource({
            "990101-1234567, 990101-*******",
            "001010-1234567, 001010-*******"
    })
    void testMaskWhenValidInputThenReturnMaskedString(String input, String expected) {
        assertEquals(expected, ssnMaskingModule.mask(input));
    }

    @ParameterizedTest
    @CsvSource({
            "9901011234567, 990101*******",
            "0010101234567, 001010*******"
    })
    void testMaskWhenValidInputWithoutHyphenThenReturnMaskedString(String input, String expected) {
        assertEquals(expected, ssnMaskingModule.mask(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "990101-12345",
            "990101-12345678",
            "990101-1234abc",
            "990101-1234@67",
            "990101 1234567",
            "901301-1234567",
            "900132-1234567",
            "900101-5234567",
            "0013101234567",
            "0013101234567",
            "0013321234567",
            "0013321234567",
            ""
    })
    void testFail_MaskWhenInvalidInputThenThrowException(String input) {
        assertThrows(MaskingServiceException.class, () -> ssnMaskingModule.mask(input));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-1234567, 7, 13, 990101-*******",
            "001010-1234567, 7, 13, 001010-*******"
    })
    void testMaskWithIndicesWhenValidInputThenReturnMaskedString(String input, int fromIndex, int toIndex, String expected) {
        assertEquals(expected, ssnMaskingModule.mask(input, fromIndex, toIndex));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-12345, 7, 13",
            "0013101234567, 7, 13"
    })
    void testMaskWithIndicesWhenInvalidInputThenThrowException(String input, int fromIndex, int toIndex) {
        assertThrows(MaskingServiceException.class, () -> ssnMaskingModule.mask(input, fromIndex, toIndex));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-1234567, 6, ******-1234567",
            "001010-1234567, 6, ******-1234567"
    })
    void testMaskFrontWhenValidInputThenReturnMaskedString(String input, int length, String expected) {
        assertEquals(expected, ssnMaskingModule.maskFront(input, length));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-12345, 6",
            "00101012345678, 6"
    })
    void testMaskFrontWhenInvalidInputThenThrowException(String input, int length) {
        assertThrows(MaskingServiceException.class, () -> ssnMaskingModule.maskFront(input, length));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-1234567, 7, 990101-*******",
            "001010-1234567, 7, 001010-*******"
    })
    void testMaskBackWhenValidInputThenReturnMaskedString(String input, int length, String expected) {
        assertEquals(expected, ssnMaskingModule.maskBack(input, length));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-12345, 7",
            "0010321234567, 7"
    })
    void testMaskBackWhenInvalidInputThenThrowException(String input, int length) {
        assertThrows(MaskingServiceException.class, () -> ssnMaskingModule.maskBack(input, length));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-1234567, \\d{7}$, 990101-*******",
            "001010-1234567, \\d{7}$, 001010-*******"
    })
    void testMaskWithRegexWhenValidInputThenReturnMaskedString(String input, String regex, String expected) {
        Pattern pattern = Pattern.compile(regex);
        assertEquals(expected, ssnMaskingModule.maskWithRegex(input, pattern));
    }

    @ParameterizedTest
    @CsvSource({
            "990101-12345, \\d{7}$",
            "0013101234567, \\d{7}$"
    })
    void testMaskWithRegexWhenInvalidInputThenThrowException(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        assertThrows(MaskingServiceException.class, () -> ssnMaskingModule.maskWithRegex(input, pattern));
    }
}