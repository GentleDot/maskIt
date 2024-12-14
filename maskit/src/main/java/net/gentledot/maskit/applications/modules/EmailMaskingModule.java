package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.applications.utils.MaskingUtil;
import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;

import java.util.regex.Pattern;

public class EmailMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private static final DataTypes DATA_TYPE = DataTypes.EMAIL;

    @Override
    public String mask(String data) {
        try {
            if (data == null) {
                throw new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST);
            }
            return data.replaceAll("(^[^@])([^@]*)(@.*$)", "$1***$3");
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        return MaskingUtil.mask(data, fromIndex, toIndex);
    }

    public String maskFront(String data, int length) {
        return maskFront(data, length);
    }

    public String maskBack(String data, int length) {
        return maskBack(data, length);
    }

    public String maskWithRegex(String data, Pattern regex) {
        return maskWithRegex(data, regex);
    }
}
