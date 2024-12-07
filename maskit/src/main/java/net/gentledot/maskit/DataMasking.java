package net.gentledot.maskit;

import net.gentledot.maskit.rules.CreditCardMaskingRule;
import net.gentledot.maskit.rules.EmailMaskingRule;
import net.gentledot.maskit.rules.MaskingRule;
import net.gentledot.maskit.rules.NameMaskingRule;
import net.gentledot.maskit.rules.PhoneNumberMaskingRule;
import net.gentledot.maskit.rules.SSNMaskingRule;

import java.util.HashMap;
import java.util.Map;

public class DataMasking {
    private final Map<String, MaskingRule> rules = new HashMap<>();

    public DataMasking() {
        // 기본 마스킹 규칙 추가
        rules.put("credit-card", new CreditCardMaskingRule());
        rules.put("ssn", new SSNMaskingRule());
        rules.put("email", new EmailMaskingRule());
        rules.put("phone-number", new PhoneNumberMaskingRule());
        rules.put("name", new NameMaskingRule());
    }

    public void addCustomRule(String key, MaskingRule rule) {
        rules.put(key, rule);
    }

    public String mask(String data, String ruleKey) {
        MaskingRule rule = rules.get(ruleKey);
        if (rule != null) {
            return rule.apply(data);
        }
        throw new IllegalArgumentException("No such masking rule: " + ruleKey);
    }
}
