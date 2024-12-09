package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class AddressMaskingRule implements MaskingRule {

    @Override
    public String apply(String data) {
        return DataTypes.ADDRESS.defaultMasking(data);
    }
}
