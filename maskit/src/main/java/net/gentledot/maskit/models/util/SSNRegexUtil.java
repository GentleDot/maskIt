package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class SSNRegexUtil {
    private SSNRegexUtil() {

    }

    public static final String YEAR_REGEX = "([0-9]{2})";
    public static final String MONTH_REGEX = "((?:0[1-9]|1[0-2]))";
    public static final String DAY_REGEX = "((?:0[1-9]|[12][0-9]|3[01]))";
    public static final String SERIAL_REGEX = "([1-4][0-9]{6})";
    public static final String SSN_REGEX = buildSSNRegex();
    public static final Pattern SSN_PATTERN = Pattern.compile(SSN_REGEX);

    public static String buildSSNRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(YEAR_REGEX)
                .append(MONTH_REGEX)
                .append(DAY_REGEX)
                .append("[-]?")
                .append(SERIAL_REGEX)
                .append("$");
        return stringBuilder.toString();
    }
}