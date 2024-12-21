package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AddressValidatorTest {

    private final AddressValidator addressValidator = new AddressValidator();

    @ParameterizedTest
    @CsvSource({
            "서울특별시 강남구 테헤란로 123",
            "부산광역시 해운대구 해운대로 456",
            "경기도 성남시 분당구 판교로 789",
            "인천광역시 남동구 논현로 101",
            "대전광역시 유성구 대학로 202",
            "울산광역시 남구 삼산로 303",
            "세종특별자치시 한누리대로 404",
            "제주특별자치도 제주시 중앙로 505",
            "강원도 춘천시 중앙로 606",
            "충청북도 청주시 상당구 상당로 707",
            "전라북도 전주시 완산구 전라대로 808",
            "경상남도 창원시 성산구 창원대로 909",
            "경상북도 포항시 남구 포항대로 1010",
            "전라남도 여수시 여수로 1111",
            "충청남도 천안시 서북구 천안대로 1212",
            "경기도 고양시 일산동구 중앙로 1313",
            "서울특별시 강남구 삼성동 123",
            "부산광역시 해운대구 우동 456",
            "경기도 성남시 분당구 정자동 789",
            "인천광역시 남동구 논현동 101",
            "대전광역시 유성구 봉명동 202",
            "울산광역시 남구 달동 303",
            "세종특별자치시 나성동 404",
            "제주특별자치도 제주시 이도이동 505",
            "강원도 춘천시 석사동 606",
            "충청북도 청주시 상당구 용암동 707",
            "전라북도 전주시 완산구 효자동 808",
            "경상남도 창원시 성산구 상남동 909",
            "경상북도 포항시 남구 대잠동 1010",
            "전라남도 여수시 학동 1111",
            "충청남도 천안시 서북구 두정동 1212",
            "경기도 고양시 일산동구 백석동 1313",
            "서울특별시 강남구",
            "서울특별시 강남구 테헤란로",
            "서울특별시 강남구 테헤란로 123-45, 삼성빌딩 101호"
    })
    void testValidAddresses(String address) {
        Assertions.assertTrue(addressValidator.isValid(address), "유효한 주소: " + address);
    }

    // 참고한 자료 : https://medium.com/daangn/%EC%A3%BC%EC%86%8C-%EC%9D%B8%EC%8B%9D%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%82%BD%EC%A7%88%EC%9D%98-%EA%B8%B0%EB%A1%9D-df2d8f82d25
    @ParameterizedTest
    @CsvSource({
            "오픈기념으로 1",
            "말씀드린대로 12",
            "종류별로 15",
            "빳데리 2",
            "허리 28",
            "가로90cm세로40",
            "Invalid Address 123",
            "12345"
    })
    void testInvalidAddresses(String address) {
        Assertions.assertFalse(addressValidator.isValid(address), "유효하지 않은 주소: " + address);
    }

    @Test
    void testFail_WhenInputNull() {
        Assertions.assertThrows(MaskingServiceException.class, () -> addressValidator.isValid(null));
    }
}