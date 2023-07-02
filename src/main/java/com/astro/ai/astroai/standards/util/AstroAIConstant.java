package com.astro.ai.astroai.standards.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstroAIConstant {

    public static final String PLANET_HOROSCOPE_API_PATH = "/horoscope/planet-details";
    public static final String API_KEY = "api_key";
    public static final String DATE_OF_BIRTH = "dob";
    public static final String TIME_OF_BIRTH = "tob";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";
    public static final String TIME_ZONE = "tz";
    public static final String LANGUAGE = "lang";
    public static final String CUSTOMER = "Customer";
    public static final String RESPONSE = "Response";
    public static final String DASHA_API_PATH = "/dashas/antar-dasha";
    public static final String PAYLOAD = "Payload";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String EMPTY_STRING = "";
    public static final String ASCENDANT = "Ascendant";

    public static final Map<Integer, String> RASINUMBER_TO_RASINAME = new HashMap<>();

    public static final Map<Integer, String> NUMBER_TO_STRING = new HashMap<>();
    public static final String NO_PLANET = "no planet";
    public static final String COMMA = ",";
    public static final String FORWARD_SLASH = "/";
    public static final String FINAL_RESPONSE = "final Response";
    public static final String REQUEST = "request";
    public static final String HEADERS = "headers";
    public static final String ERROR_RESPONSE = "error Response";

    static {
        RASINUMBER_TO_RASINAME.put(0, "Aries");
        RASINUMBER_TO_RASINAME.put(1, "Taurus");
        RASINUMBER_TO_RASINAME.put(2, "Gemini");
        RASINUMBER_TO_RASINAME.put(3, "Cancer");
        RASINUMBER_TO_RASINAME.put(4, "Leo");
        RASINUMBER_TO_RASINAME.put(5, "virgo");
        RASINUMBER_TO_RASINAME.put(6, "Libera");
        RASINUMBER_TO_RASINAME.put(7, "Scorpio");
        RASINUMBER_TO_RASINAME.put(8, "Sagittarius");
        RASINUMBER_TO_RASINAME.put(9, "Capricorn");
        RASINUMBER_TO_RASINAME.put(10, "Aquarius");
        RASINUMBER_TO_RASINAME.put(11, "Pisces");

        NUMBER_TO_STRING.put(1, "First ");
        NUMBER_TO_STRING.put(2, "Second ");
        NUMBER_TO_STRING.put(3, "Third ");
        NUMBER_TO_STRING.put(4, "Fourth ");
        NUMBER_TO_STRING.put(5, "Fifth ");
        NUMBER_TO_STRING.put(6, "Sixth ");
        NUMBER_TO_STRING.put(7, "Seventh ");
        NUMBER_TO_STRING.put(8, "Eight ");
        NUMBER_TO_STRING.put(9, "Ninth ");
        NUMBER_TO_STRING.put(10, "Tenth ");
        NUMBER_TO_STRING.put(11, "Eleventh ");
        NUMBER_TO_STRING.put(12, "Twelveth ");

    }

    public static final String FIRST_RESPONSE = "first response";
    public static final String SECOND_RESPONSE = "second response";
    public static final String THIRD_RESPONSE = "third response";
    public static final String FOURTH_RESPONSE = "fourth response";

}
