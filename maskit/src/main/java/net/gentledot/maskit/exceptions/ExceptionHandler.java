package net.gentledot.maskit.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler {

    private ExceptionHandler() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void handleException(Throwable e, String message) {
        if (e instanceof MaskingServiceException) {
            handleCustomException((MaskingServiceException) e, message);
        } else {
            handleGeneralException(e);
        }
    }

    private static void handleCustomException(MaskingServiceException ex, String message) {
        logger.error("MaskingServiceException occurred [{}] : {}", ex.getServiceError().getCode(), message);
    }

    private static void handleGeneralException(Throwable ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage());
    }
}
