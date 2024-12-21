package net.gentledot.maskit.custom;

import net.gentledot.maskit.applications.modules.MaskingModule;

import java.util.regex.Pattern;

public class CustomAddressMaskingModule implements MaskingModule {

    @Override
    public String mask(String data) {
        return customMaskAddress(data);
    }

    private String customMaskAddress(String data) {
        // 사용자 정의 마스킹 로직 예시
        // 예를 들어, 주소의 숫자를 '*'로 마스킹
        return data.replaceAll("\\d", "*");
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {

        StringBuilder masked = new StringBuilder(data);
        for (int i = fromIndex; i < toIndex && i < data.length(); i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    @Override
    public String maskFront(String data, int length) {

        StringBuilder masked = new StringBuilder(data);
        for (int i = 0; i < length && i < data.length(); i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    @Override
    public String maskBack(String data, int length) {

        StringBuilder masked = new StringBuilder(data);
        for (int i = data.length() - length; i < data.length(); i++) {
            if (i >= 0) {
                masked.setCharAt(i, '*');
            }
        }
        return masked.toString();
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {

        return regex.matcher(data).replaceAll("*");
    }
}
