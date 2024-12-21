package net.gentledot.maskit.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    private static final String EXCEPTION_HANDLER = "maskit.exceptions.ExceptionHandler";
    private static final Logger logger = LoggerFactory.getLogger(EXCEPTION_HANDLER);

    public static void handleException(Exception e, String message) throws MaskingServiceException {
        if (e instanceof MaskingServiceException) {
            handleCustomException((MaskingServiceException) e, message);
            throw (MaskingServiceException) e;
        } else {
            handleGeneralException(e);
            throw new MaskingServiceException(ServiceError.INTERNAL_SERVER_ERROR, e);
        }
    }

    private static void handleCustomException(MaskingServiceException ex, String message) {
        logger.error("MaskingServiceException occurred [{}] : {}", ex.getServiceError().getCode(), message);
    }

    private static void handleGeneralException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage());
    }
}
