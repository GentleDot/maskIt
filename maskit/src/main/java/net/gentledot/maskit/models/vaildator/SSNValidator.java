package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

public class SSNValidator implements DataValidator {
    private static final String YEAR_REGEX = "[0-9]{2}";
    private static final String MONTH_REGEX = "(?:0[1-9]|1[0-2])";
    private static final String DAY_REGEX = "(?:0[1-9]|[12][0-9]|3[01])";
    private static final String SERIAL_REGEX = "[1-4][0-9]{6}";

    private static final String SSN_REGEX = buildSSNRegex();
    private static final Pattern SSN_PATTERN = Pattern.compile(SSN_REGEX);

    private static String buildSSNRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(YEAR_REGEX)
                .append(MONTH_REGEX)
                .append(DAY_REGEX)
                .append("-")
                .append(SERIAL_REGEX)
                .append("$");
        return stringBuilder.toString();
    }

    public boolean isValid(String ssn) {
        if (ssn == null) {
            return false;
        }
        return SSN_PATTERN.matcher(ssn).matches();
    }
}
