package net.gentledot.maskit;

import net.gentledot.maskit.applications.modules.AddressMaskingModule;
import net.gentledot.maskit.applications.modules.CreditCardMaskingModule;
import net.gentledot.maskit.applications.modules.EmailMaskingModule;
import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.applications.modules.NameMaskingModule;
import net.gentledot.maskit.applications.modules.PhoneNumberMaskingModule;
import net.gentledot.maskit.applications.modules.SSNMaskingModule;
import net.gentledot.maskit.applications.service.MaskingService;
import net.gentledot.maskit.models.DataTypes;

public class DataMasking {
    private final MaskingService maskingService;

    private DataMasking(MaskingService maskingService) {
        this.maskingService = maskingService;
    }

    public static DataMaskingBuilder builder() {
        return new DataMaskingBuilder();
    }

    public MaskingModule getModule(DataTypes dataType) {
        return maskingService.getMaskingModule(dataType);
    }

    public static class DataMaskingBuilder {
        private CreditCardMaskingModule creditCardMaskingModule;
        private SSNMaskingModule ssnMaskingModule;
        private EmailMaskingModule emailMaskingModule;
        private PhoneNumberMaskingModule phoneNumberMaskingModule;
        private NameMaskingModule nameMaskingModule;
        private AddressMaskingModule addressMaskingModule;

        public DataMaskingBuilder() {
            this.creditCardMaskingModule = CreditCardMaskingModule.newInstance();
            this.ssnMaskingModule = SSNMaskingModule.newInstance();
            this.emailMaskingModule = EmailMaskingModule.newInstance();
            this.phoneNumberMaskingModule = PhoneNumberMaskingModule.newInstance();
            this.nameMaskingModule = NameMaskingModule.newInstance();
            this.addressMaskingModule = AddressMaskingModule.newInstance();
        }

        public DataMaskingBuilder phoneNumberMaskingModule(MaskingModule phoneNumberMaskingModule) {
            this.phoneNumberMaskingModule = (PhoneNumberMaskingModule) phoneNumberMaskingModule;
            return this;
        }

        public DataMaskingBuilder creditCardMaskingModule(MaskingModule creditCardMaskingModule) {
            this.creditCardMaskingModule = (CreditCardMaskingModule) creditCardMaskingModule;
            return this;
        }

        public DataMaskingBuilder ssnMaskingModule(MaskingModule ssnMaskingModule) {
            this.ssnMaskingModule = (SSNMaskingModule) ssnMaskingModule;
            return this;
        }

        public DataMaskingBuilder emailMaskingModule(MaskingModule emailMaskingModule) {
            this.emailMaskingModule = (EmailMaskingModule) emailMaskingModule;
            return this;
        }

        public DataMaskingBuilder nameMaskingModule(MaskingModule nameMaskingModule) {
            this.nameMaskingModule = (NameMaskingModule) nameMaskingModule;
            return this;
        }

        public DataMaskingBuilder addressMaskingModule(MaskingModule addressMaskingModule) {
            this.addressMaskingModule = (AddressMaskingModule) addressMaskingModule;
            return this;
        }

        public DataMasking build() {
            return new DataMasking(
                    new MaskingService(
                            creditCardMaskingModule,
                            ssnMaskingModule,
                            emailMaskingModule,
                            phoneNumberMaskingModule,
                            nameMaskingModule,
                            addressMaskingModule
                    )
            );
        }
    }
}
