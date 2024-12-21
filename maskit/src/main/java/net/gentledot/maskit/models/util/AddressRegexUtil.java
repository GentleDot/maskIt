package net.gentledot.maskit.models.util;

import java.util.regex.Pattern;

public class AddressRegexUtil {
    private AddressRegexUtil() {
    }

    public static final String SPECIAL_CITY = "\\S+(특별)?시";
    public static final String METROPOLITAN_CITY = "\\S+광역시";
    public static final String PROVINCE = "\\S+도";
    public static final String SPECIAL_SELF_GOVERNING_CITY = "\\S+특별자치시";
    public static final String SPECIAL_SELF_GOVERNING_PROVINCE = "\\S+특별자치도";
    public static final String CITY = "\\S+시";
    public static final String COUNTY = "\\S+군";
    public static final String DISTRICT = "\\S+구";
    public static final String NEIGHBORHOOD = "\\S+동";
    public static final String ROAD = "\\S+로";
    public static final String BUILDING_NUMBER = "\\d{1,4}";
    public static final String OPTIONAL_BUILDING_NUMBER = "(?:[-\\s]\\d{1,4})?";
    public static final String OPTIONAL_DETAIL = "(?:,\\s?.+)?";
    public static final String ROAD_ADDRESS_REGEX = buildRoadAddressRegex();
    public static final String NEIGHBORHOOD_ADDRESS_REGEX = buildNeighborhoodAddressRegex();
    public static final Pattern ROAD_ADDRESS_PATTERN = Pattern.compile(ROAD_ADDRESS_REGEX);
    public static final Pattern NEIGHBORHOOD_ADDRESS_PATTERN = Pattern.compile(NEIGHBORHOOD_ADDRESS_REGEX);
    // simple ver
    public static final String SIMPLE_ADDRESS_REGEX = buildSimpleAddressRegex();
    public static final String SIMPLE_ROAD_ADDRESS_REGEX = buildSimpleRoadAddressRegex();
    public static final String CITY_REGION_REGEX = buildCityRegionRegex();
    public static final Pattern SIMPLE_ADDRESS_PATTERN = Pattern.compile(SIMPLE_ADDRESS_REGEX);
    public static final Pattern SIMPLE_ROAD_ADDRESS_PATTERN = Pattern.compile(SIMPLE_ROAD_ADDRESS_REGEX);
    public static final Pattern CITY_REGION_PATTERN = Pattern.compile(CITY_REGION_REGEX);

    // 세종특별자치시
    public static final String SPECIAL_SELF_GOVERNING_CITY_ROAD_REGEX = buildSpecialSelfGoverningCityRoadRegex();
    public static final String SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_REGEX = buildSpecialSelfGoverningCityNeighborhoodRegex();
    public static final Pattern SPECIAL_SELF_GOVERNING_CITY_ROAD_PATTERN = Pattern.compile(SPECIAL_SELF_GOVERNING_CITY_ROAD_REGEX);
    public static final Pattern SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_PATTERN = Pattern.compile(SPECIAL_SELF_GOVERNING_CITY_NEIGHBORHOOD_REGEX);

    public static String buildRoadAddressRegex() {
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

    public static String buildNeighborhoodAddressRegex() {
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

    public static String buildSimpleAddressRegex() {
        return new StringBuilder()
                .append("^")
                .append(buildCityRegex())
                .append("\\s")
                .append(buildRegionRegex())
                .append("$")
                .toString();
    }

    public static String buildSimpleRoadAddressRegex() {
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

    public static String buildSpecialSelfGoverningCityRoadRegex() {
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

    public static String buildSpecialSelfGoverningCityNeighborhoodRegex() {
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
    }// Method to build the city part of the regex

    public static String buildCityRegex() {
        return String.format("(%s|%s|%s|%s|%s)", SPECIAL_CITY, METROPOLITAN_CITY, PROVINCE, SPECIAL_SELF_GOVERNING_CITY, SPECIAL_SELF_GOVERNING_PROVINCE);
    }// Method to build the region part of the regex

    public static String buildRegionRegex() {
        return String.format("(%s\\s%s|%s\\s%s|%s|%s|%s|%s|%s|%s)", CITY, DISTRICT, CITY, COUNTY, CITY, COUNTY, DISTRICT, SPECIAL_SELF_GOVERNING_CITY, SPECIAL_SELF_GOVERNING_PROVINCE, SPECIAL_SELF_GOVERNING_CITY);
    }

    public static String buildCityRegionRegex() {
        return String.format("((%s)?\\s?(%s)\\s?)",
                buildCityRegex(), buildRegionRegex());
    }

}