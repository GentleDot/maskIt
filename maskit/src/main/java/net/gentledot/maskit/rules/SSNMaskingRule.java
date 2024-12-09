package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class SSNMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return DataTypes.SSN.defaultMasking(data);
    }
}
