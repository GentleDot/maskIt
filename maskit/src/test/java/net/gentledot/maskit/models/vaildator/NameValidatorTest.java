package net.gentledot.maskit.models.vaildator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorTest {

    private NameValidator nameValidator = new NameValidator();

    @Test
    void testIsValidNameWhenInputIsValidThenReturnTrue() {
        String name = "홍길동";
        boolean result = nameValidator.isValidName(name);
        assertTrue(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputIsTooShortThenReturnFalse() {
        String name = "홍";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputIsTooLongThenReturnFalse() {
        String name = "홍istoolong동";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsInvalidCharactersThenReturnFalse() {
        String name = "홍길동123";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsSpacesThenReturnFalse() {
        String name = "홍 길 동";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsSpecialCharactersThenReturnFalse() {
        String name = "홍길동!@#";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void testIsValidNameWhenInputIsExactlyTwoCharactersThenReturnTrue() {
        String name = "홍길";
        boolean result = nameValidator.isValidName(name);
        assertTrue(result);
    }

    @Test
    void testIsValidNameWhenInputIsExactlySixCharactersThenReturnTrue() {
        String name = "홍길동홍길";
        boolean result = nameValidator.isValidName(name);
        assertTrue(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsEnglishCharactersThenReturnFalse() {
        String name = "홍길동abc";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsMixedKoreanAndEnglishCharactersThenReturnFalse() {
        String name = "홍길동abc홍";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsOnlyKoreanCharactersButTooShortThenReturnFalse() {
        String name = "가";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void falseTest_IsValidNameWhenInputContainsOnlyKoreanCharactersButTooLongThenReturnFalse() {
        String name = "가나다라마바사아";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void testIsNameWhenInputIsNullThenReturnFalse() {
        String name = null;
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

    @Test
    void testIsNameWhenInputsIBlanklThenReturnFalse() {
        String name = "";
        boolean result = nameValidator.isValidName(name);
        assertFalse(result);
    }

}