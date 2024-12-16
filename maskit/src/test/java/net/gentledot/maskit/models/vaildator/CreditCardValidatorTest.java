package net.gentledot.maskit.models.vaildator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CreditCardValidatorTest {
    private CreditCardValidator creditCardValidator = new CreditCardValidator();

    @ParameterizedTest
    @CsvSource({
            // visa
            "4111111111111111, true",
            "4111111111111112, false",
            // mastercard
            "5555555555554444, true",
            "5555555555554445, false",
            // american express
            "378282246310005, true",
            "378282246310006, false",
            // diners club
            "30569309025904, true",
            "30569309025905, false",
            //discover
            "6011111111111117, true",
            "6011111111111118, false",
            //jcb
            "3530111333300000, true",
            "3530111333300001, false"
    })
    void testIsCreditCardType(String cardNumber, boolean expected) {
        assertEquals(expected, creditCardValidator.isValidCreditCard(cardNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "4111-1111-1111-1111, true",
            "4111 1111 1111 1111, false",
            "4111-1111-1111-1112, false",
            "4111 1111 1111 1112, false"
    })
    void testIsValidCreditCardWithFormatting(String cardNumber, boolean expected) {
        assertEquals(expected, creditCardValidator.isValidCreditCard(cardNumber));
    }

    @Test
    void testIsValidCreditCardWhenNullThenFalse() {
        String nullCreditCard = null;
        assertFalse(creditCardValidator.isValidCreditCard(nullCreditCard), "Expected null credit card number to return false");
    }

    @Test
    void testIsValidCreditCardWhenEmptyThenFalse() {
        String emptyCreditCard = "";
        assertFalse(creditCardValidator.isValidCreditCard(emptyCreditCard), "Expected empty credit card number to return false");
    }

}