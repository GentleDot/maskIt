package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.models.util.NameRegexUtil;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.NameValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private final DataValidator validator;

    private NameMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static NameMaskingModule newInstance() {
        return new NameMaskingModule(new NameValidator());
    }

    public static NameMaskingModule newInstance(DataValidator validator) {
        return new NameMaskingModule(validator);
    }

    @Override
    public String mask(String data) {
        validator.isValid(data);
        try {
            Matcher matcher = NameRegexUtil.FULL_NAME_PATTERN.matcher(data);
            if (matcher.matches()) {
                String lastName = matcher.group(1); // 성
                String space = matcher.group(2) != null ? matcher.group(2) : ""; // 공백
                String firstName = matcher.group(3); // 이름

                // 이름의 길이에 따라 마스킹 처리
                if (data.length() <= 3) {
                    // 3글자 이하인 경우, 뒤의 2글자를 마스킹
                    return data.charAt(0) + maskPart(data.substring(1));
                } else {
                    // 3글자 초과인 경우, 이름 전체를 마스킹
                    return lastName + space + maskPart(firstName);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    private String maskPart(String part) {
        StringBuilder masked = new StringBuilder(part.length());
        for (int i = 0; i < part.length(); i++) {
            masked.append('*');
        }
        return masked.toString();
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        return super.maskIndex(data, fromIndex, toIndex);
    }

    @Override
    public String maskFront(String data, int length) {
        return super.maskFront(data, length);
    }

    @Override
    public String maskBack(String data, int length) {
        return super.maskBack(data, length);
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {
        return super.maskWithRegex(data, regex);
    }

}
