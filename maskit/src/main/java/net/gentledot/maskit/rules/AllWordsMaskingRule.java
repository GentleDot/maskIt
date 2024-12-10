package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class AllWordsMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        if (data == null || isBlank(data)) {
            return data;
        }
        return DataTypes.ALL.defaultMasking(data);
    }

    private boolean isBlank(String data) {
        return data.trim().isEmpty();
    }
}
