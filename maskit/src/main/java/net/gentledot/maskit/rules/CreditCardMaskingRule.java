package net.gentledot.maskit.rules;

import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;

public class CreditCardMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return DataTypes.CREDIT_CARD.defaultMasking(data);
    }
}
