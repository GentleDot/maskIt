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

    public DataMasking() {
        this.maskingService = new MaskingService();
    }

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
            this.creditCardMaskingModule = new CreditCardMaskingModule();
            this.ssnMaskingModule = new SSNMaskingModule();
            this.emailMaskingModule = new EmailMaskingModule();
            this.phoneNumberMaskingModule = new PhoneNumberMaskingModule();
            this.nameMaskingModule = new NameMaskingModule();
            this.addressMaskingModule = new AddressMaskingModule();
        }

        public DataMaskingBuilder phoneNumberMaskingModule(PhoneNumberMaskingModule phoneNumberMaskingModule) {
            this.phoneNumberMaskingModule = phoneNumberMaskingModule;
            return this;
        }

        public DataMaskingBuilder creditCardMaskingModule(CreditCardMaskingModule creditCardMaskingModule) {
            this.creditCardMaskingModule = creditCardMaskingModule;
            return this;
        }

        public DataMaskingBuilder ssnMaskingModule(SSNMaskingModule ssnMaskingModule) {
            this.ssnMaskingModule = ssnMaskingModule;
            return this;
        }

        public DataMaskingBuilder emailMaskingModule(EmailMaskingModule emailMaskingModule) {
            this.emailMaskingModule = emailMaskingModule;
            return this;
        }

        public DataMaskingBuilder nameMaskingModule(NameMaskingModule nameMaskingModule) {
            this.nameMaskingModule = nameMaskingModule;
            return this;
        }

        public DataMaskingBuilder addressMaskingModule(AddressMaskingModule addressMaskingModule) {
            this.addressMaskingModule = addressMaskingModule;
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
