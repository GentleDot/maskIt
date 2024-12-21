package net.gentledot.maskit.applications.service;

import net.gentledot.maskit.applications.modules.AddressMaskingModule;
import net.gentledot.maskit.applications.modules.CreditCardMaskingModule;
import net.gentledot.maskit.applications.modules.EmailMaskingModule;
import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.applications.modules.NameMaskingModule;
import net.gentledot.maskit.applications.modules.PhoneNumberMaskingModule;
import net.gentledot.maskit.applications.modules.SSNMaskingModule;
import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;

public class MaskingService {
    private final CreditCardMaskingModule creditCardMaskingModule;
    private final SSNMaskingModule ssnMaskingModule;
    private final EmailMaskingModule emailMaskingModule;
    private final PhoneNumberMaskingModule phoneNumberMaskingModule;
    private final NameMaskingModule nameMaskingModule;
    private final AddressMaskingModule addressMaskingModule;

    public MaskingService(CreditCardMaskingModule creditCardMaskingModule, SSNMaskingModule ssnMaskingModule, EmailMaskingModule emailMaskingModule, PhoneNumberMaskingModule phoneNumberMaskingModule, NameMaskingModule nameMaskingModule, AddressMaskingModule addressMaskingModule) {
        this.creditCardMaskingModule = creditCardMaskingModule;
        this.ssnMaskingModule = ssnMaskingModule;
        this.emailMaskingModule = emailMaskingModule;
        this.phoneNumberMaskingModule = phoneNumberMaskingModule;
        this.nameMaskingModule = nameMaskingModule;
        this.addressMaskingModule = addressMaskingModule;
    }

    public MaskingModule getMaskingModule(DataTypes ruleKey) {
        try {
            if (ruleKey == null) {
                throw new MaskingServiceException(ServiceError.MASKING_TYPE_NOT_FOUND);
            }
            switch (ruleKey) {
                case CREDIT_CARD:
                    return creditCardMaskingModule;
                case SSN:
                    return ssnMaskingModule;
                case EMAIL:
                    return emailMaskingModule;
                case PHONE_NUMBER:
                    return phoneNumberMaskingModule;
                case NAME:
                    return nameMaskingModule;
                case ADDRESS:
                    return addressMaskingModule;
                default:
                    throw new MaskingServiceException(ServiceError.MASKING_TYPE_NOT_FOUND);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "masking service error occurred.");
            throw new MaskingServiceException(ServiceError.INTERNAL_SERVER_ERROR);
        }
    }
}