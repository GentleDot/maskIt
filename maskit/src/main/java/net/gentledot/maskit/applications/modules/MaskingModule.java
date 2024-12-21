package net.gentledot.maskit.applications.modules;

import java.util.regex.Pattern;

/**
 * <h2>개요</h2>
 * MaskingModule 인터페이스는 다양한 데이터 마스킹 기능을 정의합니다.
 * 이 인터페이스를 구현하는 클래스는 특정 데이터 형식에 대한 마스킹 로직을 제공해야 합니다.
 *
 * <h2>설명</h2>
 * <p>각 마스킹 모듈은 데이터의 특정 부분을 마스킹하거나, 전체 데이터를 마스킹하는 기능을 제공합니다.</p>
 * <p>이 인터페이스는 다음과 같은 메서드를 포함합니다:</p>
 * <ul>
 *     <li><code>mask(String data)</code>: 전체 데이터를 마스킹합니다.</li>
 *     <li><code>mask(String data, int fromIndex, int toIndex)</code>: 특정 인덱스 범위만 마스킹합니다.</li>
 *     <li><code>maskFront(String data, int length)</code>: 데이터의 앞부분을 마스킹합니다.</li>
 *     <li><code>maskBack(String data, int length)</code>: 데이터의 뒷부분을 마스킹합니다.</li>
 *     <li><code>maskWithRegex(String data, Pattern regex)</code>: 정규 표현식을 사용하여 데이터를 마스킹합니다.</li>
 * </ul>
 *
 * <h2>예시</h2>
 * <pre>
 * // CreditCardMaskingModule의 예시
 * MaskingModule creditCardModule = new CreditCardMaskingModule();
 * String maskedData = creditCardModule.mask("1234-5678-9012-3456");
 * System.out.println(maskedData); // 출력: ****-****-****-3456
 *
 * // 특정 인덱스 범위 마스킹 예시
 * String partialMaskedData = creditCardModule.mask("1234-5678-9012-3456", 0, 4);
 * System.out.println(partialMaskedData); // 출력: ****-5678-9012-3456
 * </pre>
 */
public interface MaskingModule {

    /**
     * 전체 데이터를 기본 룰로 마스킹합니다.
     *
     * @param data 마스킹할 원본 데이터
     * @return 마스킹된 데이터
     * @throws net.gentledot.maskit.exceptions.MaskingServiceException 유효하지 않은 형식의 요청인 경우
     */
    String mask(String data);

    /**
     * 특정 인덱스 범위만 마스킹합니다.
     *
     * @param data      마스킹할 원본 데이터
     * @param fromIndex 마스킹을 시작할 인덱스
     * @param toIndex   마스킹을 종료할 인덱스
     * @return 마스킹된 데이터
     * @throws net.gentledot.maskit.exceptions.MaskingServiceException 유효하지 않은 형식의 요청인 경우
     */
    String mask(String data, int fromIndex, int toIndex);

    /**
     * 데이터의 앞부분을 마스킹합니다.
     *
     * @param data   마스킹할 원본 데이터
     * @param length 마스킹할 길이
     * @return 마스킹된 데이터
     * @throws net.gentledot.maskit.exceptions.MaskingServiceException 유효하지 않은 형식의 요청인 경우
     */
    String maskFront(String data, int length);


    /**
     * 데이터의 뒷부분을 마스킹합니다.
     *
     * @param data   마스킹할 원본 데이터
     * @param length 마스킹할 길이
     * @return 마스킹된 데이터
     * @throws net.gentledot.maskit.exceptions.MaskingServiceException 유효하지 않은 형식의 요청인 경우
     */
    String maskBack(String data, int length);

    /**
     * 정규 표현식을 사용하여 데이터를 마스킹합니다.
     *
     * @param data  마스킹할 원본 데이터
     * @param regex 마스킹에 사용할 정규 표현식
     * @return 마스킹된 데이터
     * @throws net.gentledot.maskit.exceptions.MaskingServiceException 유효하지 않은 형식의 요청인 경우
     */
    String maskWithRegex(String data, Pattern regex);
}
