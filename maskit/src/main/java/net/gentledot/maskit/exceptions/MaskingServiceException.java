package net.gentledot.maskit.exceptions;

public class MaskingServiceException extends RuntimeException {
    private final ServiceError serviceError;

    public MaskingServiceException(ServiceError serviceError) {
        super(serviceError.getMessage());
        this.serviceError = serviceError;
    }

    public MaskingServiceException(ServiceError serviceError, Throwable cause) {
        super(serviceError.getMessage(), cause);
        this.serviceError = serviceError;
    }

    public ServiceError getServiceError() {
        return serviceError;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaskingServiceException{");
        sb.append("serviceError=").append(serviceError);
        sb.append('}');
        return sb.toString();
    }
}
