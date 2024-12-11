package net.gentledot.maskit;

import net.gentledot.maskit.applications.modules.MaskingModule;
import net.gentledot.maskit.applications.service.MaskingService;
import net.gentledot.maskit.models.DataTypes;

public class DataMasking {
    private final MaskingService maskingService = new MaskingService();

    public MaskingModule getModule(DataTypes dataType) {
        return maskingService.getMaskingModule(dataType);
    }

}
