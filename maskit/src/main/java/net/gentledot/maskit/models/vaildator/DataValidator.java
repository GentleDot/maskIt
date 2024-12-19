package net.gentledot.maskit.models.vaildator;

public interface DataValidator {
    boolean isValid(String data);

    default boolean isEmpty(String data) {
        if (data == null) {
            return true;
        }
        return data.trim().isEmpty();
    }

    default boolean isNotEmpty(String data) {
        return !isEmpty(data);
    }
}
