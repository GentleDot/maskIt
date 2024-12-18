package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class CreditCardRegexUtil {
    private CreditCardRegexUtil() {
    }

    public static final String VISA_REGEX = "4\\d{12}(?:\\d{3})?";
    public static final String MASTERCARD_REGEX = "5[1-5]\\d{14}";
    public static final String AMEX_REGEX = "3[47]\\d{13}";
    public static final String DINERS_CLUB_REGEX = "3(?:0[0-5]|[68]\\d)\\d{11}";
    public static final String DISCOVER_REGEX = "6(?:011|5\\d{2})\\d{12}";
    public static final String JCB_REGEX = "(?:2131|1800|35\\d{3})\\d{11}";
    public static final String OPTIONAL_GROUP = "(?:-\\d{4})?";
    public static final String CREDIT_CARD_REGEX = buildCreditCardRegex();
    public static final Pattern CREDIT_CARD_PATTERN = Pattern.compile(CREDIT_CARD_REGEX);

    public static String buildCreditCardRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^(?:")
                .append(VISA_REGEX).append("|")
                .append(MASTERCARD_REGEX).append("|")
                .append(AMEX_REGEX).append("|")
                .append(DINERS_CLUB_REGEX).append("|")
                .append(DISCOVER_REGEX).append("|")
                .append(JCB_REGEX)
                .append(")")
                .append(OPTIONAL_GROUP)
                .append("$");
        return stringBuilder.toString();
    }
}