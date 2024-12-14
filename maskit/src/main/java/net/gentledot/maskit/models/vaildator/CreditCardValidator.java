package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

public class CreditCardValidator {
    private static final String VISA_REGEX = "4\\d{12}(?:\\d{3})?";
    private static final String MASTERCARD_REGEX = "5[1-5]\\d{14}";
    private static final String AMEX_REGEX = "3[47]\\d{13}";
    private static final String DINERS_CLUB_REGEX = "3(?:0[0-5]|[68]\\d)\\d{11}";
    private static final String DISCOVER_REGEX = "6(?:011|5\\d{2})\\d{12}";
    private static final String JCB_REGEX = "(?:2131|1800|35\\d{3})\\d{11}";
    private static final String OPTIONAL_GROUP = "(?:-\\d{4})?";

    private static final String CREDIT_CARD_REGEX = buildCreditCardRegex();
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile(CREDIT_CARD_REGEX);

    private static String buildCreditCardRegex() {
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

    public boolean isValidCreditCard(String creditCard) {
        if (creditCard == null) {
            return false;
        }

        String sanitizedCreditCard = creditCard.replace("-", "");
        if (!CREDIT_CARD_PATTERN.matcher(sanitizedCreditCard).matches()) {
            return false;
        }
        return isValidLuhn(sanitizedCreditCard);
    }

    // Luhn algorithm (https://en.wikipedia.org/wiki/Luhn_algorithm)
    private boolean isValidLuhn(String creditCard) {
        int sum = 0;
        boolean alternate = false;
        for (int i = creditCard.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(creditCard.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
