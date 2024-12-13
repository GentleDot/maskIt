package net.gentledot.maskit;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.models.DataTypes;
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
        String actualMaskedData = dataMasking.getModule(DataTypes.CREDIT_CARD).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("주민번호의 기본 마스킹 동작 체크")
    @Test
    void testMaskSSN() {
        String data = "001212-1234567";
        String expectedMaskedData = "001212-*******";
        String actualMaskedData = dataMasking.getModule(DataTypes.SSN).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("이메일의 기본 마스킹 동작 체크")
    @Test
    void testMaskEmail() {
        String data = "example@example.com";
        String expectedMaskedData = "e***@example.com";
        String actualMaskedData = dataMasking.getModule(DataTypes.EMAIL).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("전화번호의 기본 마스킹 동작 체크")
    @Test
    void testMaskPhoneNumber() {
        String data = "01012345678";
        String expectedMaskedData = "010****5678";
        String actualMaskedData = dataMasking.getModule(DataTypes.PHONE_NUMBER).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("이름의 기본 마스킹 동작 체크")
    @Test
    void testMaskName() {
        String data = "홍길동";
        String expectedMaskedData = "홍*";
        String actualMaskedData = dataMasking.getModule(DataTypes.NAME).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("주소의 기본 마스킹 동작 체크")
    @Test
    void testMaskAddress() {
        String data = "서울특별시 강남구 테헤란로7길 123";
        String expectedMaskedData = "서울특별시 강남구 **** 123";
        String actualMaskedData = dataMasking.getModule(DataTypes.ADDRESS).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("정의되지 않은 마스킹 형식은 예외를 발생.")
    @Test
    void failTest_MaskWhenInvalidRuleKeyThenThrowException() {
        assertThrows(MaskingServiceException.class, () -> dataMasking.getModule(null));
    }

    @DisplayName("CREDIT_CARD 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testCreditCardModuleMaskedCreditCard() {
        String data = "1234567812345678";
        String expectedMaskedData = "1234****5678";
        String actualMaskedData = dataMasking.getModule(DataTypes.CREDIT_CARD).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("SSN 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testSSNModuleMaskedSSN() {
        String data = "001212-1234567";
        String expectedMaskedData = "001212-*******";
        String actualMaskedData = dataMasking.getModule(DataTypes.SSN).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("EMAIL 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testEmailModuleMaskedEmail() {
        String data = "example@example.com";
        String expectedMaskedData = "e***@example.com";
        String actualMaskedData = dataMasking.getModule(DataTypes.EMAIL).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("PHONE_NUMBER 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testPhoneNumberModuleMaskedPhoneNumber() {
        String data = "01012345678";
        String expectedMaskedData = "010****5678";
        String actualMaskedData = dataMasking.getModule(DataTypes.PHONE_NUMBER).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("NAME 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testNameModuleMaskedName() {
        String data = "홍길동";
        String expectedMaskedData = "홍*";
        String actualMaskedData = dataMasking.getModule(DataTypes.NAME).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }

    @DisplayName("ADDRESS 데이터 타입의 마스킹 모듈 테스트")
    @Test
    void testAddressModuleMaskedAddress() {
        String data = "서울특별시 강남구 테헤란로7길 123";
        String expectedMaskedData = "서울특별시 강남구 **** 123";
        String actualMaskedData = dataMasking.getModule(DataTypes.ADDRESS).mask(data);
        assertEquals(expectedMaskedData, actualMaskedData);
    }
}