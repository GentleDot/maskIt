package net.gentledot.maskit.models.vaildator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        if (email.endsWith(".")) { // check this first - it's cheap!
            return false;
        }
        // Check the whole email address structure
        final Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        return emailMatcher.matches();
    }
}
