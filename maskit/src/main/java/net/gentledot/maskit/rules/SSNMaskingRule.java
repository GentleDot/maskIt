package net.gentledot.maskit.rules;

public class SSNMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return data.replaceAll("(\\d{6})\\d{7}", "$1*******");
    }
}
