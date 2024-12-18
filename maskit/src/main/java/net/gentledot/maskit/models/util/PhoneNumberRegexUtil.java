package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class PhoneNumberRegexUtil {
    private PhoneNumberRegexUtil() {
    }

    public static final String COUNTRY_CODE_REGEX = "(\\+82|0)";
    public static final String AREA_CODE_REGEX = "(10|2|[3-9][0-9]|19)";
    public static final String PREFIX_REGEX = "([0-9]{3,4})";
    public static final String LINE_NUMBER_REGEX = "([0-9]{4})";
    public static final String PHONE_NUMBER_REGEX = buildPhoneNumberRegex();
    public static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    public static String buildPhoneNumberRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(COUNTRY_CODE_REGEX)
                .append("[-]?")
                .append(AREA_CODE_REGEX)
                .append("[-]?")
                .append(PREFIX_REGEX)
                .append("[-]?")
                .append(LINE_NUMBER_REGEX)
                .append("$");
        return stringBuilder.toString();
    }
}