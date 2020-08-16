package com.leandromaro.santander.rest.service;

import com.leandromaro.santander.rest.client.domain.response.WeatherResponse;
import com.leandromaro.santander.rest.client.domain.response.darkSky.DarkSkyResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.helper.UrlHelper;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.respository.MeetUpRepository;
import lombok.SneakyThrows;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_HOST = "community-open-weather-map.p.rapidapi.com";
    private static final String OPEN_WEATHER_KEY = "hw5LL92VcdmshXW5REMeQyiBrO8np1hfjKUjsnYwH0r6ddFEcp";
    private static final String DARK_SKY_HOST = "dark-sky.p.rapidapi.com";
    private static final String DARK_SKY_KEY = "hw5LL92VcdmshXW5REMeQyiBrO8np1hfjKUjsnYwH0r6ddFEcp";

    private final RestTemplate restTemplate;

    private final MeetUpRepository meetUpRepository;

    public WeatherService(RestTemplateBuilder builder, MeetUpRepository meetUpRepository) {
        this.restTemplate = builder.build();
        this.meetUpRepository = meetUpRepository;
    }

    public DarkSkyResponse getWeather(Long meetUpId) {
        MeetUp meetUp = getMeetUp(meetUpId);
        ResponseEntity<WeatherResponse> meetUpCity = getMeetUpCity(meetUp);

        String meetUpDateString = getMeetUpDateString(meetUp);

        ResponseEntity<DarkSkyResponse> meetUpCityWeather = getMeetUpCityWeather(meetUpCity, meetUpDateString);

        return meetUpCityWeather.getBody();
    }

    private ResponseEntity<DarkSkyResponse> getMeetUpCityWeather(ResponseEntity<WeatherResponse> result, String meetUpDateString) {
        HttpEntity<String> darkSkyRequest = new HttpEntity<>("", UrlHelper.getHeaders(DARK_SKY_HOST,DARK_SKY_KEY));
        String lat = Double.toString(result.getBody().getCity().getCoord().getLat());
        String lon = Double.toString(result.getBody().getCity().getCoord().getLon());
        return restTemplate.exchange(URI.create(UrlHelper.buildCustomUrlByCity(lat,lon, meetUpDateString)), HttpMethod.GET, darkSkyRequest, DarkSkyResponse.class);
    }

    private String getMeetUpDateString(MeetUp meetUp) {
        String meetUpDate = meetUp.getMeetUpdate().toString();
        return meetUpDate.replace(" ", "T").replaceAll("[.]+([0-9]+)", "");
    }

    private ResponseEntity<WeatherResponse> getMeetUpCity(MeetUp meetUp) {
        String city = meetUp.getCity();
        HttpEntity<String> request = new HttpEntity<>("", UrlHelper.getHeaders(OPEN_WEATHER_HOST,OPEN_WEATHER_KEY));
        String url = UrlHelper.buildCustomUrlByCity(city);
        return restTemplate.exchange(URI.create(url), HttpMethod.GET, request, WeatherResponse.class);
    }


    private MeetUp getMeetUp(long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        return meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));
    }


    private Double getTemperatureOfMeetUp(MeetUp meetUp,  WeatherResponse weatherResponse){
        /*
        Double temp = null;

        weatherResponse.get
        for(List list: weatherResponse.getList()) {
            if(meetUp.getMeetUpDate().compareTo(list.getDate())== 0) {
                temp = (double) weatherDay.getTemp().getMax();
            }
        }
        return temp;

        */
        return null;
    }
}
