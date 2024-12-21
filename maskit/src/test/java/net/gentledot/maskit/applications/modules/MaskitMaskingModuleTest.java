package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaskitMaskingModuleTest extends MaskitMaskingModule {

    @Test
    void testMaskFront() {
        String data = "123";
        int length = 3;
        String result = maskFront(data, length);
        assertEquals("***", result);

        data = "12345";
        length = 3;
        result = maskFront(data, length);
        assertEquals("***45", result);
    }

    @Test
    void testFail_MaskFrontWithInvalidLength() {
        String data = "123";
        int length = 5;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskFront(data, length));
        assertEquals(ServiceError.MASKING_INVALID_LENGTH, exception.getServiceError());
    }

    @Test
    void testFail_MaskFrontWithNullData() {
        String data = null;
        int length = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskFront(data, length));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskFrontWithEmptyData() {
        String data = "";
        int length = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskFront(data, length));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testMaskBack() {
        String data = "123";
        int length = 3;
        String result = maskBack(data, length);
        assertEquals("***", result);

        data = "12345";
        length = 3;
        result = maskBack(data, length);
        assertEquals("12***", result);
    }

    @Test
    void testFail_MaskBackWithInvalidLength() {
        String data = "123";
        int length = 5;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskBack(data, length));
        assertEquals(ServiceError.MASKING_INVALID_LENGTH, exception.getServiceError());
    }

    @Test
    void testFail_MaskBackWithNullData() {
        String data = null;
        int length = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskBack(data, length));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskBackWithEmptyData() {
        String data = "";
        int length = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskBack(data, length));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testMaskWithRegex() {
        String data = "12345";
        Pattern regex = Pattern.compile("\\d");
        String result = maskWithRegex(data, regex);
        assertEquals("*****", result);

        data = "abcde";
        regex = Pattern.compile("\\d");
        result = maskWithRegex(data, regex);
        assertEquals("abcde", result);
    }

    @Test
    void testFail_MaskWithRegexWithNullData() {
        String data = null;
        Pattern regex = Pattern.compile("\\d");
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskWithRegex(data, regex));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskWithRegexWithNullRegex() {
        String data = "12345";
        Pattern regex = null;
        assertThrows(MaskingServiceException.class, () -> maskWithRegex(data, regex));
    }

    @Test
    void testFail_MaskIndexWhenDataIsNullThenThrowException() {
        String data = null;
        int fromIndex = 0;
        int toIndex = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskIndex(data, fromIndex, toIndex));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskIndexWhenFromIndexIsLessThanZeroThenThrowException() {
        String data = "12345";
        int fromIndex = -1;
        int toIndex = 3;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskIndex(data, fromIndex, toIndex));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testFail_MaskIndexWhenToIndexIsGreaterThanDataLengthThenThrowException() {
        String data = "12345";
        int fromIndex = 0;
        int toIndex = 6;
        MaskingServiceException exception = assertThrows(MaskingServiceException.class, () -> maskIndex(data, fromIndex, toIndex));
        assertEquals(ServiceError.MASKING_INVALID_REQUEST, exception.getServiceError());
    }

    @Test
    void testMaskIndexWhenFromIndexIsGreaterThanOrEqualToToIndexThenReturnMaskedData() {
        String data = "12345";
        int fromIndex = 3;
        int toIndex = 3;
        String result = maskIndex(data, fromIndex, toIndex);
        assertEquals("123*5", result);
    }

    @Test
    void testMaskIndexWhenDataIsValidThenReturnMaskedData() {
        String data = "12345";
        int fromIndex = 1;
        int toIndex = 4;
        String result = maskIndex(data, fromIndex, toIndex);
        assertEquals("1****", result);
    }
}