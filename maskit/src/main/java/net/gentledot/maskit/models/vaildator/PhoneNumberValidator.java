package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements DataValidator {
    private static final String COUNTRY_CODE_REGEX = "(\\+82|0)";
    private static final String AREA_CODE_REGEX = "(10|2|[3-9][0-9]|19)";
    private static final String PREFIX_REGEX = "([0-9]{3,4})";
    private static final String LINE_NUMBER_REGEX = "([0-9]{4})";

    private static final String PHONE_NUMBER_REGEX = buildPhoneNumberRegex();
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private static String buildPhoneNumberRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(COUNTRY_CODE_REGEX)
                .append("[-]?")
                .append(AREA_CODE_REGEX)
                .append("[-]?")
                .append(PREFIX_REGEX)
                .append("[-]?")
                .append(LINE_NUMBER_REGEX)
                .append("$");
        return stringBuilder.toString();
    }

    public boolean isValid(String phoneNumber) {
        if (phoneNumber == null) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null phone number is not allowed.");
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
