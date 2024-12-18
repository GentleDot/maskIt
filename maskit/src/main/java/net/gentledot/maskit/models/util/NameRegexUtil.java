package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class NameRegexUtil {
    private NameRegexUtil() {
    }

    public static final String NAME_REGEX = "^[가-힣]{2,6}$";
    public static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
}