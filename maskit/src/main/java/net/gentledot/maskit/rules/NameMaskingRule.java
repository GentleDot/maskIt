package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class NameMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return DataTypes.NAME.defaultMasking(data);
    }
}
