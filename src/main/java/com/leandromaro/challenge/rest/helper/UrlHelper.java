package com.leandromaro.challenge.rest.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public final class UrlHelper {

    private UrlHelper() {
        throw new AssertionError("Class should not be implemented");
    }

    private static final String OPEN_WEATHER_URL = "https://community-open-weather-map.p.rapidapi.com/forecast/daily";

    private static final String DARK_SKY_WEATHER_URL = "https://dark-sky.p.rapidapi.com/";

    private static final String country = "ar";

    private static final String units = "units=metric";

    private static final String cnt = "cnt=1";

    public static final String AND = "&";
    public static final String QUERY = "?q=";
    public static final String COMMA = ",";

    public static String buildCustomUrlByCity(String cityName){
        String encodedWitheSpaceCity = cityName.replaceAll("\\s+", "%20");
        String params = new StringBuilder()
                .append(OPEN_WEATHER_URL)
                .append(QUERY)
                .append(encodedWitheSpaceCity)
                .append(AND)
                .append(country)
                .append(AND)
                .append(units)
                .append(AND)
                .append(cnt).toString();
        return params;
    }

    public static String buildCustomUrlByCity(String latitude, String longitude, String meetUpDate){
        return  DARK_SKY_WEATHER_URL +
                latitude +
                COMMA +
                longitude +
                COMMA +
                meetUpDate;
    }

    public static HttpHeaders getHeaders(String host, String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", host);
        headers.set("x-rapidapi-key", key);
        return headers;
    }
}
