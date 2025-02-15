# MaskIt!

## 개요

maskit은

- 민감한 정보를 보호하기 위해 데이터의 원래의 값을 숨기는 기능을 제공합니다.

## 기능 요약

- **카드번호 마스킹**: 신용카드 번호의 일부를 마스킹합니다.
- **주민번호 마스킹**: 주민등록번호의 일부를 마스킹합니다.
- **이메일 마스킹**: 이메일 주소의 일부를 마스킹합니다.
- **전화번호 마스킹**: 전화번호의 일부를 마스킹합니다.
- **이름 마스킹**: 이름의 일부를 마스킹합니다.
- **주소 마스킹**: 주소의 일부를 마스킹합니다.

## 설치

- MaskIt 라이브러리는 `jitpack.io`를 통해 사용할 수 있습니다.
  - JitPack에 대한 세부적인 내용은 [JitPack 문서](https://docs.jitpack.io/)를 참조해주세요.
- 프로젝트에 `jitpack.io`를 설정한 후 MaskIt 라이브러리를 추가할 수 있습니다.

### Maven 설정

1. JitPack repository를 추가하고

```xml

<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

2. dependency를 추가합니다.

```xml

<dependency>
  <groupId>com.github.GentleDot</groupId>
  <artifactId>maskIt</artifactId>
  <version>0.0.2-alpha</version>
</dependency>
```

### Gradle 설정

1. JitPack repository를 추가하고

```groovy
repositories {
  mavenCentral()
  maven { url 'https://jitpack.io' }
}
```

2. dependency를 추가합니다.

```groovy
    dependencies {
  implementation 'com.github.GentleDot:maskIt:0.1.1'
}
```

## 사용법

MaskIt 라이브러리를 사용하여 다양한 데이터 유형에 대해 마스킹을 수행할 수 있습니다.

현재 지원되는 데이터 유형은 다음과 같습니다:

- 신용카드번호 (CREDIT_CARD)
- 주민등록번호 (SSN)
- 이메일 (EMAIL)
- 전화번호 (PHONE_NUMBER)
- 이름 (NAME)
- 주소 (ADDRESS)

### 1. DataMasking 인스턴스 생성

```java
import net.gentledot.maskit.DataMasking;

public class MaskingExample {
  public static void main(String[] args) {
    DataMasking masking = DataMasking.builder().build(); // 기본 제공 기능으로 구현
  }
}
```

- MaskIt 라이브러리를 사용하려면 DataMasking 클래스의 인스턴스를 생성해야 합니다.

- 또는 원하는 마스킹 기능을 구현한 MaskingModule을 추가하여 설정 하실 수 있습니다.
  - 구체적인 예시는 [examples/masking_example2](./examples/masking_example2) 에서 확인하실 수 있습니다.

```java
import net.gentledot.maskit.DataMasking;

public class MaskingExample {
  public static void main(String[] args) {
    DataMasking masking = DataMasking.builder()
                    .addressMaskingModule(new CustomAddressMaskingModule())
                    .build(); // address module을 별도 생성 후 구성
  }
}
```

### 2. 데이터 유형 설정

```java
import net.gentledot.maskit.models.DataTypes;

public class MaskingExample {
  public static void main(String[] args) {
    DataMasking dataMasking = DataMasking.builder().build(); // 기본 제공 기능으로 구현     
    MaskingModule module = dataMasking.getModule(DataTypes.PHONE_NUMBER);
  }
}
```

- DataTypes enum 클래스를 사용하여 MaskIt 라이브러리에서 지원하는 데이터 유형을 정의할 수 있습니다.

```java
public enum DataTypes {
  CREDIT_CARD,  // 신용카드 번호
  SSN,          // 주민등록번호
  EMAIL,        // 이메일 주소
  PHONE_NUMBER, // 전화번호
  NAME,         // 이름
  ADDRESS,      // 주소
  CUSTOM        // 사용자 정의 데이터 유형
}
```

### 3 데이터 마스킹

- mask() 메서드를 사용하여 전화번호의 일부를 마스킹합니다.

```java
import net.gentledot.maskit.models.DataTypes;

public class MaskingExample {
  public static void main(String[] args) {
    DataMasking dataMasking = DataMasking.builder().build(); // 기본 제공 기능으로 구현     
    MaskingModule module = dataMasking.getModule(DataTypes.PHONE_NUMBER);
    
    String data = "01012345678";
    String actualMaskedData = module.mask(data); // 010****5678
  }
}
```

마스킹은 크게 5가지의 방식으로 제공됩니다.

1. 기본 제공 마스킹
2. 특정 인덱스 범위 내에서 데이터 마스킹
3. 데이터의 앞부분 마스킹
4. 데이터의 뒷부분 마스킹
5. 사용자 정의 정규 표현식을 사용하여 데이터 마스킹

## 라이선스

이 프로젝트는 MIT 라이선스 하에 라이선스가 부여됩니다.
자세한 내용은 [LICENSE](./LICENSE) 파일을 참조하세요.
