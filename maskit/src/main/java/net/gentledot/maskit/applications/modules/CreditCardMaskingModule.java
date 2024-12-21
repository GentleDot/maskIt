package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.models.vaildator.CreditCardValidator;
import net.gentledot.maskit.models.vaildator.DataValidator;

import java.util.regex.Pattern;

public class CreditCardMaskingModule extends MaskitMaskingModule implements MaskingModule {
    public static final int REMAIN_DIGIT_LENGTH = 4;
    private final DataValidator validator;

    private CreditCardMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static CreditCardMaskingModule newInstance() {
        return new CreditCardMaskingModule(new CreditCardValidator());
    }

    public static CreditCardMaskingModule newInstance(DataValidator validator) {
        return new CreditCardMaskingModule(validator);
    }

    @Override
    public String mask(String data) {
        validator.isValid(data);
        try {
            StringBuilder maskedData = new StringBuilder(data);
            for (int i = 0; i < data.length() - REMAIN_DIGIT_LENGTH; i++) {
                if (Character.isDigit(data.charAt(i))) {
                    maskedData.setCharAt(i, '*');
                }
            }

            return maskedData.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        validator.isValid(data);
        return super.maskIndex(data, fromIndex, toIndex);
    }

    @Override
    public String maskFront(String data, int length) {
        validator.isValid(data);
        return super.maskFront(data, length);
    }

    @Override
    public String maskBack(String data, int length) {
        validator.isValid(data);
        return super.maskBack(data, length);
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {
        validator.isValid(data);
        return super.maskWithRegex(data, regex);
    }
}
