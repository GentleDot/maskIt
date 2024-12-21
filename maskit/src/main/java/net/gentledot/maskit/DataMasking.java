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


/**
 * <h2>개요</h2>
 * DataMasking은 다양한 데이터 마스킹 모듈을 관리하고,
 * 사용자가 정의한 모듈을 추가하여 사용할 수 있는 기능을 제공합니다.
 *
 * <h2>설명</h2>
 * <p>DataMasking 인스턴스는 {@link DataMaskingBuilder}를 사용하여 생성할 수 있습니다.</p>
 * <p> DataMasking.builder().build(); // 기본 제공 기능으로 구현 </p>
 *
 * <p>DataMaskingBuilder를 사용하여 각 마스킹 모듈을 설정할 수 있습니다. 기본적으로 제공되는 모듈은 다음과 같습니다:</p>
 * <ul>
 *     <li>신용카드 마스킹 모듈</li>
 *     <li>주민등록번호 마스킹 모듈</li>
 *     <li>이메일 마스킹 모듈</li>
 *     <li>전화번호 마스킹 모듈</li>
 *     <li>이름 마스킹 모듈</li>
 *     <li>주소 마스킹 모듈</li>
 * </ul>
 *
 * <p>DataMasking 인스턴스를 사용하여 특정 데이터 타입에 대한 마스킹 모듈을 사용자 정의로 생성하고 적용할 수도 있습니다.
 * 가져온 모듈을 사용하여 데이터를 마스킹할 수 있습니다.</p>
 *
 * <h2>예시</h2>
 * <pre>
 * DataMasking dataMasking = DataMasking.builder()
 *     .creditCardMaskingModule(new CustomCreditCardMaskingModule())
 *     .build();
 *
 * MaskingModule creditCardModule = dataMasking.getModule(DataTypes.CREDIT_CARD);
 * String maskedData = creditCardModule.mask("1234-5678-9012-3456");
 * System.out.println(maskedData); // 출력: ****-****-****-3456
 * </pre>
 */
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
        private MaskingModule creditCardMaskingModule;
        private MaskingModule ssnMaskingModule;
        private MaskingModule emailMaskingModule;
        private MaskingModule phoneNumberMaskingModule;
        private MaskingModule nameMaskingModule;
        private MaskingModule addressMaskingModule;

        public DataMaskingBuilder() {
            this.creditCardMaskingModule = CreditCardMaskingModule.newInstance();
            this.ssnMaskingModule = SSNMaskingModule.newInstance();
            this.emailMaskingModule = EmailMaskingModule.newInstance();
            this.phoneNumberMaskingModule = PhoneNumberMaskingModule.newInstance();
            this.nameMaskingModule = NameMaskingModule.newInstance();
            this.addressMaskingModule = AddressMaskingModule.newInstance();
        }

        public DataMaskingBuilder phoneNumberMaskingModule(MaskingModule phoneNumberMaskingModule) {
            this.phoneNumberMaskingModule = phoneNumberMaskingModule;
            return this;
        }

        public DataMaskingBuilder creditCardMaskingModule(MaskingModule creditCardMaskingModule) {
            this.creditCardMaskingModule = creditCardMaskingModule;
            return this;
        }

        public DataMaskingBuilder ssnMaskingModule(MaskingModule ssnMaskingModule) {
            this.ssnMaskingModule = ssnMaskingModule;
            return this;
        }

        public DataMaskingBuilder emailMaskingModule(MaskingModule emailMaskingModule) {
            this.emailMaskingModule = emailMaskingModule;
            return this;
        }

        public DataMaskingBuilder nameMaskingModule(MaskingModule nameMaskingModule) {
            this.nameMaskingModule = nameMaskingModule;
            return this;
        }

        public DataMaskingBuilder addressMaskingModule(MaskingModule addressMaskingModule) {
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
