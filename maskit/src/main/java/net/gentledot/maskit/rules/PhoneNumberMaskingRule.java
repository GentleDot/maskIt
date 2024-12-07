package net.gentledot.maskit.rules;

public class PhoneNumberMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return data.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
