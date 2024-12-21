package net.gentledot.maskit.applications.modules;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.models.util.EmailRegexUtil;
import net.gentledot.maskit.models.vaildator.DataValidator;
import net.gentledot.maskit.models.vaildator.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMaskingModule extends MaskitMaskingModule implements MaskingModule {
    private final DataValidator validator;

    private EmailMaskingModule(DataValidator validator) {
        this.validator = validator;
    }

    public static EmailMaskingModule newInstance() {
        return new EmailMaskingModule(new EmailValidator());
    }

    public static EmailMaskingModule newInstance(DataValidator validator) {
        return new EmailMaskingModule(validator);
    }

    @Override
    public String mask(String data) {
        validator.isValid(data);
        try {
            StringBuilder maskedEmail = new StringBuilder();
            Matcher matcher = EmailRegexUtil.EMAIL_PATTERN.matcher(data);
            if (matcher.find()) {
                String localPart = matcher.group(1);
                String domainPart = matcher.group(2);
                String tld = matcher.group(4);

                String maskedLocalPart = maskPart(localPart);
                String maskedDomainPart = maskPart(domainPart);

                return maskedLocalPart + "@" + maskedDomainPart + "." + tld;
            }
            return maskedEmail.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking error occurred.");
        }
        return data;
    }

    private String maskPart(String part) {
        StringBuilder masked = new StringBuilder(part.length());
        for (int i = 0; i < part.length(); i++) {
            masked.append('*');
        }
        return masked.toString();
    }

    @Override
    public String mask(String data, int fromIndex, int toIndex) {
        validator.isValid(data);
        return super.maskIndex(data, fromIndex, toIndex);
    }

    @Override
    public String maskFront(String data, int length) {
        validator.isValid(data);
        return maskFront(data, length);
    }

    @Override
    public String maskBack(String data, int length) {
        validator.isValid(data);
        return maskBack(data, length);
    }

    @Override
    public String maskWithRegex(String data, Pattern regex) {
        validator.isValid(data);
        return maskWithRegex(data, regex);
    }
}
