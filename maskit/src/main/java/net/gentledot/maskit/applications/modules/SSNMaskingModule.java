package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.applications.utils.MaskingUtil;
import net.gentledot.maskit.models.DataTypes;

import java.util.regex.Pattern;

public class SSNMaskingModule implements MaskingModule {
    private static final DataTypes DATA_TYPE = DataTypes.SSN;

    @Override
    public String mask(String data) {
        String regex;
        String replacement;
        if (data.contains("-")) {
            regex = "(\\d{6})-(\\d{7})";
            replacement = "$1-*******";
        } else {
            regex = "(\\d{6})(\\d{7})";
            replacement = "$1*******";
        }
        return MaskingUtil.mask(data, regex, replacement);
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        return MaskingUtil.mask(data, fromIndex, toIndex);
    }

    @Override
    public String maskFront(String data, int length) {
        return MaskingUtil.maskFront(data, length);
    }

    @Override
    public String maskBack(String data, int length) {
        return MaskingUtil.maskBack(data, length);
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {
        return MaskingUtil.maskWithRegex(data, regex);
    }
}
