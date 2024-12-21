package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class NameRegexUtil {
    private NameRegexUtil() {
    }

    public static final String LAST_NAME_REGEX = "^([가-힣]{1,2})";
    public static final String FIRST_NAME_REGEX = "([가-힣\\s]{1,4})$";

    public static final String FULL_NAME_REGEX = LAST_NAME_REGEX + "(\\s?)" + FIRST_NAME_REGEX;
    public static final Pattern FULL_NAME_PATTERN = Pattern.compile(FULL_NAME_REGEX);
}