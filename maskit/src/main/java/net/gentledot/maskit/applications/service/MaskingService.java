package net.gentledot.maskit.applications.service;

import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.DataTypes;


/**
 * <h2>개요</h2>
 * MaskingService는 다양한 마스킹 모듈을 관리하고,
 * 주어진 데이터 타입에 따라 적절한 마스킹 모듈을 반환하는 서비스입니다.
 *
 * <h2>설명</h2>
 * <p>이 클래스는 여러 데이터 타입에 대한 마스킹 모듈을 인스턴스 변수로 보유하고 있으며,
 * 사용자가 요청한 데이터 타입에 따라 해당 마스킹 모듈을 반환합니다.</p>
 * <p>지원하는 데이터 타입은 다음과 같습니다:</p>
 * <ul>
 *     <li>신용카드 (CREDIT_CARD)</li>
 *     <li>주민등록번호 (SSN)</li>
 *     <li>이메일 (EMAIL)</li>
 *     <li>전화번호 (PHONE_NUMBER)</li>
 *     <li>이름 (NAME)</li>
 *     <li>주소 (ADDRESS)</li>
 * </ul>
 *
 * <h2>예시</h2>
 * <pre>
 *
 * // 신용카드 마스킹 모듈 가져오기
 * DataMasking dataMasking = DataMasking.builder().build(); // 기본 제공 기능으로 구현
 * MaskingModule module = dataMasking.getModule(DataTypes.CREDIT_CARD);
 * String maskedData = creditCardModule.mask("1234-5678-9012-3456");
 * System.out.println(maskedData); // 출력: ****-****-****-3456
 * </pre>
 */
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