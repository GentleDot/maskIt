package net.gentledot.maskit.rules;

public class NameMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return data.replaceAll("(^.)(.*)", "$1*");
    }
}
