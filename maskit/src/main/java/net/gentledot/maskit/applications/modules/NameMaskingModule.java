package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;

import java.util.regex.Pattern;

public class NameMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private static final DataTypes DATA_TYPE = DataTypes.NAME;

    @Override
    public String mask(String data) {
        try {
            if (data == null) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            }
            return data.replaceAll("(^.)(.*)", "$1*");
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        if (data == null || fromIndex < 0 || toIndex > data.length() || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Invalid indices for masking");
        }
        StringBuilder masked = new StringBuilder(data);
        for (int i = fromIndex; i < toIndex; i++) {
            masked.setCharAt(i, '*');
        }
        return masked.toString();
    }

    public String maskFront(String data, int length) {
        return super.maskFront(data, length);
    }

    public String maskBack(String data, int length) {
        return super.maskBack(data, length);
    }

    public String maskWithRegex(String data, Pattern regex) {
        return super.maskWithRegex(data, regex);
    }

}
