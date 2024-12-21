package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.SSNRegexUtil;

public class SSNValidator implements DataValidator {

    public boolean isValid(String ssn) {
        if (isEmpty(ssn)) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null ssn number is not allowed.");
        }
        boolean matches = SSNRegexUtil.SSN_PATTERN.matcher(ssn).matches();

        if (!matches) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "Invalid SSN format.");
        }
        return true;
    }
}
