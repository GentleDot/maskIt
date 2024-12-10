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
    ALL(data -> {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    });

    private final Function<String, String> defaultMasking;

    DataTypes(UnaryOperator<String> defaultMasking) {
        this.defaultMasking = defaultMasking;
    }

    public String defaultMasking(String data) {
        if (data == null || isBlank(data)) {
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

    private boolean isBlank(String data) {
        return data.trim().isEmpty();
    }
}
