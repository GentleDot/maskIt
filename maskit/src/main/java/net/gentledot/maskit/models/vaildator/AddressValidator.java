package net.gentledot.maskit.models.vaildator;

import java.util.regex.Pattern;

class AddressValidator implements DataValidator {
    private static final String SPECIAL_CITY = "\\S+특별시";
    private static final String METROPOLITAN_CITY = "\\S+광역시";
    private static final String PROVINCE = "\\S+도";
    private static final String SPECIAL_SELF_GOVERNING_CITY = "\\S+특별자치시";
    private static final String SPECIAL_SELF_GOVERNING_PROVINCE = "\\S+특별자치도";
    private static final String CITY = "\\S+시";
    private static final String COUNTY = "\\S+군";
    private static final String DISTRICT = "\\S+구";
    private static final String NEIGHBORHOOD = "\\S+동";
    private static final String ROAD = "\\S+로";
    private static final String BUILDING_NUMBER = "\\d{1,4}";
    private static final String OPTIONAL_BUILDING_NUMBER = "(?:[-\\s]\\d{1,4})?";
    private static final String OPTIONAL_DETAIL = "(?:,\\s?.+)?";

    private static final String ROAD_ADDRESS_REGEX = buildRoadAddressRegex();
    private static final String NEIGHBORHOOD_ADDRESS_REGEX = buildNeighborhoodAddressRegex();
    private static final Pattern ROAD_ADDRESS_PATTERN = Pattern.compile(ROAD_ADDRESS_REGEX);
    private static final Pattern NEIGHBORHOOD_ADDRESS_PATTERN = Pattern.compile(NEIGHBORHOOD_ADDRESS_REGEX);

    // simple ver
    private static final String SIMPLE_ADDRESS_REGEX = buildSimpleAddressRegex();
    private static final String SIMPLE_ROAD_ADDRESS_REGEX = buildSimpleRoadAddressRegex();
    private static final Pattern SIMPLE_ADDRESS_PATTERN = Pattern.compile(SIMPLE_ADDRESS_REGEX);
    private static final Pattern SIMPLE_ROAD_ADDRESS_PATTERN = Pattern.compile(SIMPLE_ROAD_ADDRESS_REGEX);


    // 세종특별자치시
    private static final String SPECIAL_SELF_GOVERNING_CITY_ROAD_REGEX = buildSpecialSelfGoverningCityRoadRegex();
    private static final String SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_REGEX = buildSpecialSelfGoverningCityNeighborhoodRegex();
    private static final Pattern SPECIAL_SELF_GOVERNING_CITY_ROAD_PATTERN = Pattern.compile(SPECIAL_SELF_GOVERNING_CITY_ROAD_REGEX);
    private static final Pattern SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_PATTERN = Pattern.compile(SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_REGEX);


    private static String buildRoadAddressRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("\\s")
                .append(ROAD)
                .append("\\s")
                .append(BUILDING_NUMBER)
                .append(OPTIONAL_BUILDING_NUMBER)
                .append(OPTIONAL_DETAIL)
                .append("$");
        return stringBuilder.toString();
    }

    private static String buildNeighborhoodAddressRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("\\s")
                .append(NEIGHBORHOOD)
                .append("\\s")
                .append(BUILDING_NUMBER)
                .append(OPTIONAL_BUILDING_NUMBER)
                .append(OPTIONAL_DETAIL)
                .append("$");
        return stringBuilder.toString();
    }

    private static String buildSimpleAddressRegex() {
        return new StringBuilder()
                .append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("$")
                .toString();
    }

    private static String buildSimpleRoadAddressRegex() {
        return new StringBuilder()
                .append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("\\s")
                .append(ROAD)
                .append("$")
                .toString();
    }

    private static String buildSpecialSelfGoverningCityRoadRegex() {
        return new StringBuilder()
                .append("^")
                .append(SPECIAL_SELF_GOVERNING_CITY)
                .append("\\s")
                .append(ROAD)
                .append("\\s")
                .append(BUILDING_NUMBER)
                .append(OPTIONAL_BUILDING_NUMBER)
                .append(OPTIONAL_DETAIL)
                .append("$")
                .toString();
    }

    private static String buildSpecialSelfGoverningCityNeighborhoodRegex() {
        return new StringBuilder()
                .append("^")
                .append(SPECIAL_SELF_GOVERNING_CITY)
                .append("\\s")
                .append(NEIGHBORHOOD)
                .append("\\s")
                .append(BUILDING_NUMBER)
                .append(OPTIONAL_BUILDING_NUMBER)
                .append(OPTIONAL_DETAIL)
                .append("$")
                .toString();
    }

    // Method to build the city part of the regex
    private static String buildCityRegex() {
        return String.format("(%s|%s|%s|%s|%s)", SPECIAL_CITY, METROPOLITAN_CITY, PROVINCE, SPECIAL_SELF_GOVERNING_CITY, SPECIAL_SELF_GOVERNING_PROVINCE);
    }

    // Method to build the region part of the regex
    private static String buildRegionRegex() {
        return String.format("(%s\\s%s|%s\\s%s|%s|%s|%s|%s|%s|%s)", CITY, DISTRICT, CITY, COUNTY, CITY, COUNTY, DISTRICT, SPECIAL_SELF_GOVERNING_CITY, SPECIAL_SELF_GOVERNING_PROVINCE, SPECIAL_SELF_GOVERNING_CITY);
    }

    public boolean isValid(String address) {
        if (address == null) {
            return false;
        }
        return ROAD_ADDRESS_PATTERN.matcher(address).matches() ||
               NEIGHBORHOOD_ADDRESS_PATTERN.matcher(address).matches() ||
               SPECIAL_SELF_GOVERNING_CITY_ROAD_PATTERN.matcher(address).matches() ||
               SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_PATTERN.matcher(address).matches() ||
               SIMPLE_ADDRESS_PATTERN.matcher(address).matches() ||
               SIMPLE_ROAD_ADDRESS_PATTERN.matcher(address).matches();
    }
}
