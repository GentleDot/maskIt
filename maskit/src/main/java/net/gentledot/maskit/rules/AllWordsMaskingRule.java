package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class AllWordsMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        if (data == null || data.isBlank()) {
            return data;
        }
        return DataTypes.ALL.defaultMasking(data);
    }
}
