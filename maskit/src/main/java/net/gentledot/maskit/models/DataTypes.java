package net.gentledot.maskit.models;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public enum DataTypes {
    CREDIT_CARD(data -> data.replaceAll("(\\d{4})\\d{8}(\\d{4})", "$1****$2")),
    SSN(data -> data.replaceAll("(\\d{6})\\d{7}", "$1*******")),
    EMAIL(data -> data.replaceAll("(^[^@])([^@]*)(@.*$)", "$1***$3")),
    PHONE_NUMBER(data -> data.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),
    NAME(data -> data.replaceAll("(^.)(.*)", "$1*")),
    ADDRESS(data -> data.replaceAll("([가-힣]+(로|길))\\s?\\d*", "****")),
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
