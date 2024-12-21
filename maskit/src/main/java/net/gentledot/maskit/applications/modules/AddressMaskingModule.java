package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.models.util.AddressRegexUtil;
import net.gentledot.maskit.models.vaildator.AddressValidator;
import net.gentledot.maskit.models.vaildator.DataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private final DataValidator validator;

    private AddressMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static AddressMaskingModule newInstance() {
        return new AddressMaskingModule(new AddressValidator());
    }

    public static AddressMaskingModule newInstance(DataValidator validator) {
        return new AddressMaskingModule(validator);
    }

    @Override
    public String mask(String data) {
        validator.isValid(data);
        try {
            return maskAddress(data);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
            return null; // 이 줄은 도달하지 않음
        }
    }

    private String maskAddress(String data) {
        Pattern pattern = AddressRegexUtil.CITY_REGION_PATTERN;
        Matcher matcher = pattern.matcher(data);
        StringBuilder result = new StringBuilder();

        if (matcher.find()) {
            String substring = data.substring(matcher.start(), matcher.end());
            result.append(substring);

            if (substring.length() <= data.length()) {
                String tails = data.substring(matcher.end());
                result.append(maskGroup(tails));
            }
        }

        return result.toString();
    }

    private String maskGroup(String group) {
        StringBuilder masked = new StringBuilder(group.length());
        for (int i = 0; i < group.length(); i++) {
            masked.append('*');
        }
        return masked.toString();
    }


    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        validator.isValid(data);
        return super.maskIndex(data, fromIndex, toIndex);
    }

    @Override
    public String maskFront(String data, int length) {
        validator.isValid(data);
        return super.maskFront(data, length);
    }

    @Override
    public String maskBack(String data, int length) {
        validator.isValid(data);
        return super.maskBack(data, length);
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {
        validator.isValid(data);
        return super.maskWithRegex(data, regex);
    }
}
