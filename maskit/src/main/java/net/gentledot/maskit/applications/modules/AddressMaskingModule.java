package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
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
        Pattern pattern = Pattern.compile("([가-힣]+\\d?([로길]))");
        Matcher matcher = pattern.matcher(data);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String maskedGroup = maskGroup(matcher.group(1));
            matcher.appendReplacement(result, maskedGroup);
        }
        matcher.appendTail(result);
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
