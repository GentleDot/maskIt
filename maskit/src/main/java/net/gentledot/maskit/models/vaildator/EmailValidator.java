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

        if (email.endsWith(".")) { // check this first - it's cheap!
            return false;
        }
        // Check the whole email address structure
        final Matcher emailMatcher = EmailRegexUtil.EMAIL_PATTERN.matcher(email);
        return emailMatcher.matches();
    }
}
