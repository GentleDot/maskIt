package net.gentledot.maskit.applications.service;

import net.gentledot.maskit.applications.modules.AddressMaskingModule;
import net.gentledot.maskit.applications.modules.CreditCardMaskingModule;
import net.gentledot.maskit.applications.modules.EmailMaskingModule;
import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.applications.modules.NameMaskingModule;
import net.gentledot.maskit.applications.modules.PhoneNumberMaskingModule;
import net.gentledot.maskit.applications.modules.SSNMaskingModule;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;

public class MaskingService {

    private final CreditCardMaskingModule creditCardMaskingModule = new CreditCardMaskingModule();
    private final SSNMaskingModule ssnMaskingModule = new SSNMaskingModule();
    private final EmailMaskingModule emailMaskingModule = new EmailMaskingModule();
    private final PhoneNumberMaskingModule phoneNumberMaskingModule = new PhoneNumberMaskingModule();
    private final NameMaskingModule nameMaskingModule = new NameMaskingModule();
    private final AddressMaskingModule addressMaskingModule = new AddressMaskingModule();

    public MaskingModule getMaskingModule(DataTypes ruleKey) {
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
    }
}