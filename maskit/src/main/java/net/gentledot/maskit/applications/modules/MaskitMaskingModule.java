package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MaskitMaskingModule {

    // 데이터의 앞부분을 마스킹
    protected String maskFront(String data, int length) {
        try {
            if (isEmpty(data)) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            } else if (length > data.length()) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_LENGTH);
            }
            StringBuilder masked = new StringBuilder(data);
            for (int i = 0; i < length; i++) {
                masked.setCharAt(i, '*');
            }
            return masked.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking (front direction) error occurred.");
        }
        return data;
    }

    // 데이터의 뒷부분을 마스킹
    protected String maskBack(String data, int length) {
        try {
            if (isEmpty(data)) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            } else if (length > data.length()) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_LENGTH);
            }
            StringBuilder masked = new StringBuilder(data);
            for (int i = data.length() - length; i < data.length(); i++) {
                masked.setCharAt(i, '*');
            }
            return masked.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking (back direction) error occurred.");
        }
        return data;
    }

    // 정규 표현식을 사용하여 데이터를 마스킹
    protected String maskWithRegex(String data, Pattern regex) {
        try {
            if (isEmpty(data) || regex == null) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            }
            Matcher matcher = regex.matcher(data);
            StringBuffer result = new StringBuffer();
            while (matcher.find()) {
                String matchedGroup = matcher.group();
                StringBuilder masked = new StringBuilder(matchedGroup.length());
                for (int i = 0; i < matchedGroup.length(); i++) {
                    masked.append('*');
                }
                matcher.appendReplacement(result, masked.toString());
            }
            matcher.appendTail(result);
            return result.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking (regex) error occurred.");
        }
        return data;
    }


    // index 범위만큼 마스킹
    protected String maskIndex(String data, int fromIndex, int toIndex) {
        if (isEmpty(data) || fromIndex < 0 || toIndex > data.length() || fromIndex > toIndex) {
            throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
        }
        StringBuilder maskedData = new StringBuilder(data);
        for (int i = fromIndex; i <= toIndex; i++) {
            maskedData.setCharAt(i, '*');
        }
        return maskedData.toString();
    }

    protected boolean isEmpty(String data) {
        if (data == null) {
            return true;
        }
        return data.trim().isEmpty();
    }

    protected boolean isNotEmpty(String data) {
        return !isEmpty(data);
    }


}
