package net.gentledot.maskit.applications.service;

import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;

public class MaskingService {
    private final MaskingModule creditCardMaskingModule;
    private final MaskingModule ssnMaskingModule;
    private final MaskingModule emailMaskingModule;
    private final MaskingModule phoneNumberMaskingModule;
    private final MaskingModule nameMaskingModule;
    private final MaskingModule addressMaskingModule;

    public MaskingService(MaskingModule creditCardMaskingModule, MaskingModule ssnMaskingModule, MaskingModule emailMaskingModule, MaskingModule phoneNumberMaskingModule, MaskingModule nameMaskingModule, MaskingModule addressMaskingModule) {
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