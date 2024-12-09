package net.gentledot.maskit.exceptions;

public enum ServiceError {
    MASKING_INVALID_REQUEST("1001", "Invalid masking request."),
    MASKING_TYPE_NOT_FOUND("2001", "No such masking type."),
    BAD_REQUEST("9400", "bad request."),
    INTERNAL_SERVER_ERROR("9500", "Internal Server Error.");


    private final String code;
    private final String message;

    ServiceError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
