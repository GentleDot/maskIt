package net.gentledot.maskit;

import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.models.DataTypes;

public class Main {
    public static void main(String[] args) {
        DataMasking masking = DataMasking.builder().build(); // 기본 제공 기능으로 구현
        MaskingModule nameMaskingModule = masking.getModule(DataTypes.NAME);
        String masked = nameMaskingModule.mask("홍길동"); // 홍**
    }
}