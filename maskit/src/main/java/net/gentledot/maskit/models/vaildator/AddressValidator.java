package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

public class AddressValidator {
    private static final String SPECIAL_CITY = "\\S+특별시";
    private static final String METROPOLITAN_CITY = "\\S+광역시";
    private static final String PROVINCE = "\\S+도";
    private static final String CITY = "\\S+시";
    private static final String COUNTY = "\\S+군";
    private static final String DISTRICT = "\\S+구";
    private static final String TOWN = "\\S+읍";
    private static final String VILLAGE = "\\S+면";
    private static final String NEIGHBORHOOD = "\\S+동";
    private static final String ROAD = "\\S+로";
    private static final String STREET = "\\S+길";
    private static final String BUILDING_NUMBER = "\\d{1,4}";
    private static final String OPTIONAL_BUILDING_NUMBER = "(?:[-\\s]\\d{1,4})?";
    private static final String OPTIONAL_DETAIL = "(?:,\\s?.+)?";

    private static final String ADDRESS_REGEX = buildAddressRegex();
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(ADDRESS_REGEX);

    private static String buildAddressRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("\\s")
                .append(buildAreaRegex())
                .append("\\s")
                .append(BUILDING_NUMBER)
                .append(OPTIONAL_BUILDING_NUMBER)
                .append(OPTIONAL_DETAIL)
                .append("$");
        return stringBuilder.toString();
    }

    // Method to build the city part of the regex
    private static String buildCityRegex() {
        return String.format("(%s|%s|%s)", SPECIAL_CITY, METROPOLITAN_CITY, PROVINCE);
    }

    // Method to build the region part of the regex
    private static String buildRegionRegex() {
        return String.format("(%s|%s|%s)", CITY, COUNTY, DISTRICT);
    }

    // Method to build the area part of the regex
    private static String buildAreaRegex() {
        return String.format("(%s|%s|%s|%s|%s)", TOWN, VILLAGE, NEIGHBORHOOD, ROAD, STREET);
    }

    public boolean isValidAddress(String address) {
        if (address == null) {
            return false;
        }
        return ADDRESS_PATTERN.matcher(address).matches();
    }
}
