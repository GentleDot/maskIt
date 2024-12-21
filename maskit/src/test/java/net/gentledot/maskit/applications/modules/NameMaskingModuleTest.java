package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameMaskingModuleTest {

    private final NameMaskingModule nameMaskingModule = NameMaskingModule.newInstance();

    @ParameterizedTest
    @CsvSource({
            "홍길동, 홍**",
            "홍 길동, 홍 **",
            "김철수, 김**",
            "이순 신, 이순 *",
            "최 길동, 최 **",
            "선우용녀, 선우**",
            "남궁민수, 남궁**"
    })
    void testMaskingName(String input, String expected) {
        String result = nameMaskingModule.mask(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "박"
    })
    void testFail_InvalidName(String input) {
        assertThrows(MaskingServiceException.class, () -> {
            nameMaskingModule.mask(input);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "홍길동, 1, 2, 홍**",
            "김철수, 0, 1, **수",
            "이순신, 2, 2, 이순*"
    })
    void testMaskingNameWithIndex(String input, int fromIndex, int toIndex, String expected) {
        String result = nameMaskingModule.mask(input, fromIndex, toIndex);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "홍길동, 2, **동",
            "김철수, 3, ***",
            "이순신, 1, *순신"
    })
    void testMaskingNameFront(String input, int length, String expected) {
        String result = nameMaskingModule.maskFront(input, length);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "홍길동, 2, 홍**",
            "김철수, 3, ***",
            "이순신, 1, 이순*"
    })
    void testMaskingNameBack(String input, int length, String expected) {
        String result = nameMaskingModule.maskBack(input, length);
        assertEquals(expected, result);
    }

    @Test
    void testMaskingNameWithRegex() {
        String input = "홍길동";
        Pattern regex = Pattern.compile("길");
        String expected = "홍*동";
        String result = nameMaskingModule.maskWithRegex(input, regex);

        assertEquals(expected, result);
    }
}