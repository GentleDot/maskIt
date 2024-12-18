package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class EmailRegexUtil {
    private EmailRegexUtil() {
    }

    public static final String NO_LEADING_DOT = "(?!\\.)";     // No leading dot
    public static final String NO_TRAILING_DOT = "(?!.*\\.@)"; // No dot at the end of local part
    public static final String NO_CONSECUTIVE_DOTS = "(?!.*\\.\\.)"; // No consecutive dots
    public static final String LOCAL_PART = "[A-Za-z0-9+_.-]+"; // Local part
    public static final String AT_SYMBOL = "@";                // At symbol
    public static final String DOMAIN_NAME = "(?!-)[A-Za-z0-9-]+(?<!-)"; // Domain name without leading/trailing dash
    public static final String DOT_BEFORE_TLD = "\\.";         // Dot before TLD
    public static final String TLD = "[A-Za-z]{2,13}";           // Top-level domain (TLD)  : max length  13

    public static final String EMAIL_REGEX = new StringBuilder()
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
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
}