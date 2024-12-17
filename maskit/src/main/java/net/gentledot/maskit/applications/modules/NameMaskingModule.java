package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.NameValidator;

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
            return data.replaceAll("(^.)(.*)", "$1*");
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
