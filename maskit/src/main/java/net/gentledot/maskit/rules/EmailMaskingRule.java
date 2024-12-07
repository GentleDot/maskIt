package net.gentledot.maskit.rules;

public class EmailMaskingRule implements MaskingRule {
    @Override
    public String apply(String data) {
        return data.replaceAll("(^[^@])([^@]*)(@.*$)", "$1***$3");
    }
}
