package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

public class PhoneNumberValidator {
    private static final String COUNTRY_CODE_REGEX = "(\\+82|0)";
    private static final String AREA_CODE_REGEX = "(10|2|[3-9][0-9])";
    private static final String PREFIX_REGEX = "([0-9]{3,4})";
    private static final String LINE_NUMBER_REGEX = "([0-9]{4})";

    // Combined regex pattern for phone number
    private static final String PHONE_NUMBER_REGEX = buildPhoneNumberRegex();
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private static String buildPhoneNumberRegex() {
        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^")
                .append(COUNTRY_CODE_REGEX)
                .append(AREA_CODE_REGEX)
                .append("-?")
                .append(PREFIX_REGEX)
                .append("-?")
                .append(LINE_NUMBER_REGEX)
                .append("$");
        return regexBuilder.toString();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
