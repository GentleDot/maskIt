package net.gentledot.maskit;

import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.custom.CustomAddressMaskingModule;
import net.gentledot.maskit.models.DataTypes;

public class Main {
    public static void main(String[] args) {
        DataMasking masking = DataMasking.builder()
                .addressMaskingModule(new CustomAddressMaskingModule())
                .build(); // address module을 별도 생성 후 구성

        MaskingModule addressMaskingModule = masking.getModule(DataTypes.ADDRESS);

        // 주소 마스킹 수행
        String address = "123 Main St, Springfield, IL 62704";
        String maskedAddress = addressMaskingModule.mask(address);

        // 결과 출력
        System.out.println("Masked Address: " + maskedAddress); // *** Main St, Springfield, IL *****
    }
}