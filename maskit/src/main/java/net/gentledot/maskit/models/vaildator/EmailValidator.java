package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.EmailRegexUtil;

import java.util.regex.Matcher;

public class EmailValidator implements DataValidator {

    public boolean isValid(String email) {
        if (email == null) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null credit card number is not allowed.");
        }

        if (email == null || email.endsWith(".")) { // check this first - it's cheap!
            throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
        }

        final Matcher emailMatcher = EmailRegexUtil.EMAIL_PATTERN.matcher(email);

        if (!emailMatcher.matches()) {
            throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
        }
        return true;
    }
}
