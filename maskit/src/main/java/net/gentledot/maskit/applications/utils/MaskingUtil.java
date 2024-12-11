package net.gentledot.maskit.applications.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingUtil {
    private MaskingUtil() {
    }

    public static String mask(String data, String regex, String replacement) {
        if (data == null) {
            return null;
        }
        return data.replaceAll(regex, replacement);
    }

    public static String mask(String data, int fromIndex, int toIndex) {
        if (data == null || fromIndex < 0 || toIndex > data.length() || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Invalid indices for masking");
        }
        StringBuilder masked = new StringBuilder(data);
        for (int i = fromIndex; i < toIndex; i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    public static String maskFront(String data, int length) {
        if (data == null || length < 0 || length > data.length()) {
            throw new IllegalArgumentException("Invalid length for masking");
        }
        StringBuilder masked = new StringBuilder(data);
        for (int i = 0; i < length; i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    public static String maskBack(String data, int length) {
        if (data == null || length < 0 || length > data.length()) {
            throw new IllegalArgumentException("Invalid length for masking");
        }
        StringBuilder masked = new StringBuilder(data);
        for (int i = data.length() - length; i < data.length(); i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    public static String maskWithRegex(String data, Pattern regex) {
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
    }

    public static boolean isBlank(String data) {
        return data.trim().isEmpty();
    }
}
