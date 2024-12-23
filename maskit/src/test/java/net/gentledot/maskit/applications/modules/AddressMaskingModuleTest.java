package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressMaskingModuleTest {

    private final AddressMaskingModule addressMaskingModule = AddressMaskingModule.newInstance();

    @ParameterizedTest
    @CsvSource({
            "서울특별시 강남구 테헤란로 123, 서울특별시 강남구 ********",
            "서울시 강남구 테헤란로 123, 서울시 강남구 ********",
            "경기도 성남시 분당구 판교로 789, 경기도 성남시 분당구 *******",
            "세종특별자치시 한누리대로 404, 세종특별자치시 *********",
            "제주특별자치도 제주시 중앙로 505, 제주특별자치도 제주시 *******",
            "강원도 춘천시 중앙로 606, 강원도 춘천시 *******",
            "서울특별시 강남구, 서울특별시 강남구"
    })
    void testMaskWithVariousAddresses(String address, String expectedMaskedAddress) {
        String maskedAddress = addressMaskingModule.mask(address);

        assertNotNull(maskedAddress);
        assertEquals(expectedMaskedAddress, maskedAddress);
    }

    @ParameterizedTest
    @CsvSource({
            "서울특별시 강남구 테헤란로 123, 0, 5, ******강남구 테헤란로 123",
            "서울시 강남구 테헤란로 123, 3, 6, 서울시**** 테헤란로 123",
            "경기도 성남시 분당구 판교로 789, 4, 8, 경기도 *****당구 판교로 789",
            "세종특별자치시 한누리대로 404, 0, 7, ********한누리대로 404",
            "제주특별자치도 제주시 중앙로 505, 5, 10, 제주특별자****** 중앙로 505",
            "강원도 춘천시 중앙로 606, 3, 6, 강원도**** 중앙로 606"
    })
    void testMaskWithFromToIndex(String address, int fromIndex, int toIndex, String expectedMaskedAddress) {
        String maskedAddress = addressMaskingModule.mask(address, fromIndex, toIndex);

        assertNotNull(maskedAddress);
        assertEquals(expectedMaskedAddress, maskedAddress);
    }

    @Test
    void testFail_MaskWhenNullInputThenThrowMaskingServiceException() {
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> addressMaskingModule.mask(null));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskWhenEmptyInputThenThrowMaskingServiceException() {
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> addressMaskingModule.mask(""));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }
}