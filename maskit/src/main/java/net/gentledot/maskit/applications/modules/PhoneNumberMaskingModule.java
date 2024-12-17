package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.PhoneNumberValidator;

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
        try {
            return data.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
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
