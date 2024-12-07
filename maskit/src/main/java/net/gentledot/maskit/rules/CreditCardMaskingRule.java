package net.gentledot.maskit.rules;

public class CreditCardMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return data.replaceAll("(\\d{4})\\d{8}(\\d{4})", "$1****$2");
    }
}
