package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.NameRegexUtil;

public class NameValidator implements DataValidator {

    public boolean isValid(String name) {
        if (isEmpty(name)) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null name is not allowed.");
        }

        if (name.length() < 2 || name.length() > 6) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "name length must be between 2 and 6.");
        }

        return NameRegexUtil.FULL_NAME_PATTERN.matcher(name).matches();
    }
}
