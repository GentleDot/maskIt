package net.gentledot.maskit.models;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public enum DataTypes {
    CREDIT_CARD(data -> data.replaceAll("(\\d{4})\\d{8}(\\d{4})", "$1****$2")),
    SSN(data -> {
        String regex;
        String replacement;
        if (data.contains("-")) {
            regex = "(\\d{6})-(\\d{7})";
            replacement = "$1-*******";
        } else {
            regex = "(\\d{6})(\\d{7})";
            replacement = "$1*******";
        }
        return data.replaceAll(regex, replacement);
    }),
    EMAIL(data -> data.replaceAll("(^[^@])([^@]*)(@.*$)", "$1***$3")),
    PHONE_NUMBER(data -> data.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),
    NAME(data -> data.replaceAll("(^.)(.*)", "$1*")),
    ADDRESS(data -> data.replaceAll("([가-힣]+\\d?(로|길))", "****")),
    ALL(data -> "*".repeat(data.length()));

    private final Function<String, String> defaultMasking;

    DataTypes(UnaryOperator<String> defaultMasking) {
        this.defaultMasking = defaultMasking;
    }

    public String defaultMasking(String data) {
        if (data == null || data.isBlank()) {
            return data;
        }
        return defaultMasking.apply(data);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataTypes{");
        sb.append("defaultMasking=").append(defaultMasking);
        sb.append('}');
        return sb.toString();
    }
}
