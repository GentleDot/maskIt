package net.gentledot.maskit;

import net.gentledot.maskit.custom.CustomAddressMaskingModule;

public class Main {
    public static void main(String[] args) {
        DataMasking masking = DataMasking.builder()
                .addressMaskingModule(new CustomAddressMaskingModule())
                .build(); // address module을 별도 생성 후 구성
    }
}