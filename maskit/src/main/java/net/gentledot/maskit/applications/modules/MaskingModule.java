package net.gentledot.maskit.applications.modules;

import java.util.regex.Pattern;

public interface MaskingModule {
    // 전체 데이터를 기본 룰로 마스킹
    String mask(String data);

    // 특정 인덱스 범위 내에서 데이터를 마스킹
    String mask(String data, int fromIndex, int toIndex);

    // 데이터의 앞부분을 마스킹
    String maskFront(String data, int length);

    // 데이터의 뒷부분을 마스킹
    String maskBack(String data, int length);

    // 정규 표현식을 사용하여 데이터를 마스킹
    String maskWithRegex(String data, Pattern regex);
}
