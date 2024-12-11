package net.gentledot.maskit.applications.utils;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingUtil {
    private MaskingUtil() {
    }

    public static String mask(String data, String regex, String replacement) {
        try {
            if (data == null) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            }
            return data.replaceAll(regex, replacement);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    public static String mask(String data, int fromIndex, int toIndex) {
        try {
            if (data == null || fromIndex < 0 || toIndex > data.length() || fromIndex >= toIndex) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_INDEX);
            }
            StringBuilder masked = new StringBuilder(data);
            for (int i = fromIndex; i < toIndex; i++) {
                masked.setCharAt(i, '*');
            }
            return masked.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking (range) error occurred.");
        }

        return data;
    }

    public static String maskFront(String data, int length) {
        try {
            if (data == null || length < 0 || length > data.length()) {
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

    public static String maskBack(String data, int length) {
        try {
            if (data == null || length < 0 || length > data.length()) {
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

    public static String maskWithRegex(String data, Pattern regex) {
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

    public static boolean isBlank(String data) {
        return data.trim().isEmpty();
    }
}
