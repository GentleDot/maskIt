package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.PhoneNumberRegexUtil;

public class PhoneNumberValidator implements DataValidator {

    public boolean isValid(String phoneNumber) {
        if (phoneNumber == null) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null phone number is not allowed.");
        }
        return PhoneNumberRegexUtil.PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
