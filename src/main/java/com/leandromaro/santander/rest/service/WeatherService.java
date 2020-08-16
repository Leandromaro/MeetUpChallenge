package com.leandromaro.santander.rest.service;

import com.leandromaro.santander.rest.client.domain.response.WeatherResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.respository.MeetUpRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class WeatherService {

    private static final String WEATHER_URL = "https://community-open-weather-map.p.rapidapi.com/forecast/daily";

    private static final String country = "ar";

    private static final String units = "units=metric";

    private static final String cnt = "cnt=16";

    private static final String HOST = "community-open-weather-map.p.rapidapi.com";

    private static final String KEY = "092c123636msh32777ac17332e7dp10f955jsn6a262fd6540b";
    public static final String AND = "&";
    public static final String QUERY = "?q=";

    private final RestTemplate restTemplate;

    private final MeetUpRepository meetUpRepository;

    public WeatherService(RestTemplateBuilder builder, MeetUpRepository meetUpRepository) {
        this.restTemplate = builder.build();
        this.meetUpRepository = meetUpRepository;
    }

    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", HOST);
        headers.set("x-rapidapi-key", KEY);
        return headers;
    }

    public WeatherResponse getWeather(Long meetUpId) {
        MeetUp meetUp = getMeetUp(meetUpId);
        String city = meetUp.getCity();
        HttpEntity<String> request = new HttpEntity<>("", getHeaders());
        String url = buildCustomUrlByCity(city);
        ResponseEntity<WeatherResponse> result = restTemplate.exchange(URI.create(url), HttpMethod.GET, request, WeatherResponse.class);
        return result.getBody();
    }

    private MeetUp getMeetUp(long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        return meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));
    }

    private String buildCustomUrlByCity(String city){
        String encodedWitheSpaceCity = city.replaceAll("\\s+", "%20");
        String params = new StringBuilder()
                .append(WEATHER_URL)
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
}
