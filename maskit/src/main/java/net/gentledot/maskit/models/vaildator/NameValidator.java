package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

public class NameValidator implements DataValidator {
    private static final String NAME_REGEX = "^[가-힣]{2,6}$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    public boolean isValid(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() < 2 || name.length() > 6) {
            return false; // 2 ~ 6 자
        }

        return NAME_PATTERN.matcher(name).matches();
    }
}
