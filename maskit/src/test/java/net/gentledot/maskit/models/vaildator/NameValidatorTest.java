package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {

    private NameValidator nameValidator = new NameValidator();

    @Test
    void testisValidWhenInputIsValidThenReturnTrue() {
        String name = "홍길동";
        boolean result = nameValidator.isValid(name);
        assertTrue(result);
    }

    @Test
    void falseTest_isValidWhenInputIsTooShortThenReturnFalse() {
        String name = "홍";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void falseTest_isValidWhenInputIsTooLongThenReturnFalse() {
        String name = "홍istoolong동";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void falseTest_isValidWhenInputContainsInvalidCharactersThenReturnFalse() {
        String name = "홍길동123";
        boolean result = nameValidator.isValid(name);
        assertFalse(result);
    }

    @Test
    void testIsValidWhenInputContainsSpacesThenReturnTrue() {
        String name = "홍 길 동";
        boolean result = nameValidator.isValid(name);
        assertTrue(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsSpecialCharactersThenReturnFalse() {
        String name = "홍길동!@#";
        boolean result = nameValidator.isValid(name);
        assertFalse(result);
    }

    @Test
    void testisValidWhenInputIsExactlyTwoCharactersThenReturnTrue() {
        String name = "홍길";
        boolean result = nameValidator.isValid(name);
        assertTrue(result);
    }

    @Test
    void testisValidWhenInputIsExactlySixCharactersThenReturnTrue() {
        String name = "홍길동홍길";
        boolean result = nameValidator.isValid(name);
        assertTrue(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsEnglishCharactersThenReturnFalse() {
        String name = "홍길동abc";
        boolean result = nameValidator.isValid(name);
        assertFalse(result);
    }

    @Test
    void falseTest_isValidWhenInputContainsMixedKoreanAndEnglishCharactersThenReturnFalse() {
        String name = "홍길동abc홍";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void falseTest_isValidWhenInputContainsOnlyKoreanCharactersButTooShortThenReturnFalse() {
        String name = "가";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void falseTest_isValidWhenInputContainsOnlyKoreanCharactersButTooLongThenReturnFalse() {
        String name = "가나다라마바사아";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void testIsNameWhenInputsIBlanklThenReturnFalse() {
        String name = "";
        assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(name));
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> nameValidator.isValid(null));
    }
}