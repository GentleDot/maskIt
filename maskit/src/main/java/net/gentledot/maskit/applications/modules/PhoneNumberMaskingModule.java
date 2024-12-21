package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.PhoneNumberRegexUtil;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.PhoneNumberValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private final DataValidator validator;

    private PhoneNumberMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static PhoneNumberMaskingModule newInstance() {
        return new PhoneNumberMaskingModule(new PhoneNumberValidator());
    }

    public static PhoneNumberMaskingModule newInstance(DataValidator validator) {
        return new PhoneNumberMaskingModule(validator);
    }


    @Override
    public String mask(String data) {
        validator.isValid(data);

        Matcher matcher = PhoneNumberRegexUtil.PHONE_NUMBER_PATTERN.matcher(data);

        StringBuilder masked = new StringBuilder(data);
        int start = -1;
        int end = -1;

        // Find the start and end indices of the line number
        if (matcher.find()) {
            start = matcher.start(4); // Start index of the line number
            end = matcher.end(4); // End index of the line number
        }

        if (start == -1 || end == -1) {
            throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
        }

        for (int i = 0; i < start; i++) {
            if (Character.isDigit(masked.charAt(i))) {
                masked.setCharAt(i, '*');
            }
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
