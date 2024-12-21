package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.SSNRegexUtil;

public class SSNValidator implements DataValidator {

    public boolean isValid(String ssn) {
        if (ssn == null) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null ssn number is not allowed.");
        }
        return SSNRegexUtil.SSN_PATTERN.matcher(ssn).matches();
    }
}