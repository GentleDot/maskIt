package net.gentledot.maskit;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataMaskingTest {
    private final DataMasking dataMasking = new DataMasking();

    @DisplayName("카드번호의 기본 마스킹 동작 체크")
    @Test
    void testMaskCreditCard() {
        String data = "1234567812345678";
        String expectedMaskedData = "1234****5678";
        String actualMaskedData = dataMasking.mask(data, "CREDIT_CARD");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("주민번호의 기본 마스킹 동작 체크")
    @Test
    void testMaskSSN() {
        String data = "001212-1234567";
        String expectedMaskedData = "001212-*******";
        String actualMaskedData = dataMasking.mask(data, "SSN");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("이메일의 기본 마스킹 동작 체크")
    @Test
    void testMaskEmail() {
        String data = "example@example.com";
        String expectedMaskedData = "e***@example.com";
        String actualMaskedData = dataMasking.mask(data, "EMAIL");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("전화번호의 기본 마스킹 동작 체크")
    @Test
    void testMaskPhoneNumber() {
        String data = "01012345678";
        String expectedMaskedData = "010****5678";
        String actualMaskedData = dataMasking.mask(data, "PHONE_NUMBER");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("이름의 기본 마스킹 동작 체크")
    @Test
    void testMaskName() {
        String data = "홍길동";
        String expectedMaskedData = "홍*";
        String actualMaskedData = dataMasking.mask(data, "NAME");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("주소의 기본 마스킹 동작 체크")
    @Test
    void testMaskAddress() {
        String data = "서울특별시 강남구 테헤란로7길 123";
        String expectedMaskedData = "서울특별시 강남구 **** 123";
        String actualMaskedData = dataMasking.mask(data, "ADDRESS");
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("전체 마스킹 동작 테스트")
    @Test
    void testMaskAll() {
        String data = "This is a test.";
        String expectedMaskedData = "***************";
        String actualMaskedData = dataMasking.mask(data, "ALL");
        assertEquals(expectedMaskedData, actualMaskedData);
    }


    @DisplayName("정의되지 않은 마스킹 형식은 예외를 발생.")
    @Test
    void failTest_MaskWhenInvalidRuleKeyThenThrowException() {
        String data = "1234567812345678";
        String invalidRuleKey = "INVALID_RULE";
        assertThrows(MaskingServiceException.class, () -> dataMasking.mask(data, invalidRuleKey));
    }

    @DisplayName("마스킹 형식 미지정시 예외를 발생")
    @Test
    void testMaskWhenNullRuleKeyThenThrowException() {
        String data = "1234567812345678";
        String nullRuleKey = null;
        assertThrows(MaskingServiceException.class, () -> dataMasking.mask(data, nullRuleKey));
    }
}