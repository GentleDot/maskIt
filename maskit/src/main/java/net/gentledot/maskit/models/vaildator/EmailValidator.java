package net.gentledot.maskit.models.vaildator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements DataValidator {
    private static final String NO_LEADING_DOT = "(?!\\.)";     // No leading dot
    private static final String NO_TRAILING_DOT = "(?!.*\\.@)"; // No dot at the end of local part
    private static final String NO_CONSECUTIVE_DOTS = "(?!.*\\.\\.)"; // No consecutive dots
    private static final String LOCAL_PART = "[A-Za-z0-9+_.-]+"; // Local part
    private static final String AT_SYMBOL = "@";                // At symbol
    private static final String DOMAIN_NAME = "(?!-)[A-Za-z0-9-]+(?<!-)"; // Domain name without leading/trailing dash
    private static final String DOT_BEFORE_TLD = "\\.";         // Dot before TLD
    private static final String TLD = "[A-Za-z]{2,13}";           // Top-level domain (TLD)  : max length  13

    private static final String EMAIL_REGEX = new StringBuilder()
            .append("^")
            .append(NO_LEADING_DOT)
            .append(NO_TRAILING_DOT)
            .append(NO_CONSECUTIVE_DOTS)
            .append(LOCAL_PART)
            .append(AT_SYMBOL)
            .append(DOMAIN_NAME)
            .append("(")
            .append(DOT_BEFORE_TLD)
            .append(TLD) // https://www.iana.org/domains/root/db
            .append(")+")
            .append("$")
            .toString();
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValid(String email) {
        if (email == null) {
            return false;
        }

        if (email.endsWith(".")) { // check this first - it's cheap!
            return false;
        }
        // Check the whole email address structure
        final Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        return emailMatcher.matches();
    }
}
