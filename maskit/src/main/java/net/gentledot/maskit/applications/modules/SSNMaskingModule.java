package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.SSNRegexUtil;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.SSNValidator;

import java.util.regex.Matcher;
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

        Matcher matcher = SSNRegexUtil.SSN_PATTERN.matcher(data);
        if (!matcher.matches()) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "Invalid SSN");
        }


        String maskedSerial = matcher.group(4).replaceAll("\\d", "*");
        return new StringBuilder(data)
                .replace(matcher.start(4), matcher.end(4), maskedSerial)
                .toString();
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
