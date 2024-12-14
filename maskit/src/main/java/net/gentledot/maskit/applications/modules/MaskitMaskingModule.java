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
            if (isEmpty(data) || length > data.length()) {
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
            if (isEmpty(data) || length > data.length()) {
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
            if (data == null || regex == null) {
                throw new IllegalArgumentException("Invalid arguments for masking");
            }
            Matcher matcher = regex.matcher(data);
            StringBuffer result = new StringBuffer();
            while (matcher.find()) {
                StringBuilder masked = new StringBuilder();
                for (int i = 0; i < matcher.group().length(); i++) {
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
