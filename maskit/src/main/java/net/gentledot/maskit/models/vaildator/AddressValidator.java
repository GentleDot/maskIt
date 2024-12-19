package net.gentledot.maskit.models.vaildator;

import net.gentledot.maskit.exceptions.ExceptionHandler;
import net.gentledot.maskit.exceptions.MaskingServiceException;
import net.gentledot.maskit.exceptions.ServiceError;
import net.gentledot.maskit.models.util.AddressRegexUtil;

public class AddressValidator implements DataValidator {
    public boolean isValid(String address) {
        if (isEmpty(address)) {
            ExceptionHandler.handleException(new MaskingServiceException(ServiceError.MASKING_INVALID_REQUEST), "null address is not allowed.");
        }
        return AddressRegexUtil.ROAD_ADDRESS_PATTERN.matcher(address).matches() ||
               AddressRegexUtil.NEIGHBORHOOD_ADDRESS_PATTERN.matcher(address).matches() ||
               AddressRegexUtil.SPECIAL_SELF_GOVERNING_CITY_ROAD_PATTERN.matcher(address).matches() ||
               AddressRegexUtil.SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_PATTERN.matcher(address).matches() ||
               AddressRegexUtil.SIMPLE_ADDRESS_PATTERN.matcher(address).matches() ||
               AddressRegexUtil.SIMPLE_ROAD_ADDRESS_PATTERN.matcher(address).matches();
    }
}
