package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.CreditCardRegexUtil;

public class CreditCardValidator implements DataValidator {

    public boolean isValid(String creditCard) {
        if (creditCard == null) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null credit card number is not allowed.");
        }

        String sanitizedCreditCard = creditCard.replace("-", "");
        if (!CreditCardRegexUtil.CREDIT_CARD_PATTERN.matcher(sanitizedCreditCard).matches()) {
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
