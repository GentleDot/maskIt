package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class EmailMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return DataTypes.EMAIL.defaultMasking(data);
    }
}
