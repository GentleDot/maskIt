package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.SSNValidator;

import java.util.regex.Pattern;

public class SSNMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private final DataValidator validator;

    private SSNMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static SSNMaskingModule newInstance() {
        return new SSNMaskingModule(new SSNValidator());
    }

    public static SSNMaskingModule newInstance(DataValidator validator) {
        return new SSNMaskingModule(validator);
    }

    @Override
    public String mask(String data) {
        validator.isValid(data);

        String regex;
        String replacement;

        try {
            if (data.contains("-")) {
                regex = "(\\d{6})-(\\d{7})";
                replacement = "$1-*******";
            } else {
                regex = "(\\d{6})(\\d{7})";
                replacement = "$1*******";
            }
            return data.replaceAll(regex, replacement);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        if (super.isEmpty(data) || fromIndex < 0 || toIndex > data.length() || fromIndex >= toIndex) {
            throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
        }
        StringBuilder masked = new StringBuilder(data);
        for (int i = fromIndex; i < toIndex; i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
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
