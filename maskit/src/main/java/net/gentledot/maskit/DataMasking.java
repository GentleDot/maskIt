package net.gentledot.maskit;

import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.interfaces.MaskingRule;
import net.gentledot.maskit.models.DataTypes;
import net.gentledot.maskit.rules.AddressMaskingRule;
import net.gentledot.maskit.rules.AllWordsMaskingRule;
import net.gentledot.maskit.rules.CreditCardMaskingRule;
import net.gentledot.maskit.rules.EmailMaskingRule;
import net.gentledot.maskit.rules.NameMaskingRule;
import net.gentledot.maskit.rules.PhoneNumberMaskingRule;
import net.gentledot.maskit.rules.SSNMaskingRule;

import java.util.HashMap;
import java.util.Map;

public class DataMasking {
    private final Map<DataTypes, MaskingRule> rules = new HashMap<>();

    public DataMasking() {
        // 기본 마스킹 규칙 추가
        rules.put(DataTypes.CREDIT_CARD, new CreditCardMaskingRule());
        rules.put(DataTypes.SSN, new SSNMaskingRule());
        rules.put(DataTypes.EMAIL, new EmailMaskingRule());
        rules.put(DataTypes.PHONE_NUMBER, new PhoneNumberMaskingRule());
        rules.put(DataTypes.NAME, new NameMaskingRule());
        rules.put(DataTypes.ADDRESS, new AddressMaskingRule());
        rules.put(DataTypes.ALL, new AllWordsMaskingRule());
    }

    public String mask(String data, String ruleKey) {
        DataTypes dataTypes;
        try {
            dataTypes = DataTypes.valueOf(ruleKey);
        } catch (Exception e) {
            throw new MaskingServiceException(ServiceError.MASKING_TYPE_NOT_FOUND);
        }

        return mask(data, dataTypes);
    }

    public String mask(String data, DataTypes ruleKey) {
        MaskingRule rule = rules.get(ruleKey);
        if (rule == null) {
            throw new MaskingServiceException(ServiceError.MASKING_TYPE_NOT_FOUND);
        }
        return rule.apply(data);
    }
}
