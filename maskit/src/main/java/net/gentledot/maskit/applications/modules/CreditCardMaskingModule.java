package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.applications.utils.MaskingUtil;
import net.gentledot.maskit.models.DataTypes;

import java.util.regex.Pattern;

public class CreditCardMaskingModule implements MaskingModule {
    private static final DataTypes DATA_TYPE = DataTypes.CREDIT_CARD;

    @Override
    public String mask(String data) {
        return MaskingUtil.mask(data, "(\\d{4})\\d{8}(\\d{4})", "$1****$2");
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
