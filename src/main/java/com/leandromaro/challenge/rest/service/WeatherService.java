package com.leandromaro.challenge.rest.service;

import com.leandromaro.challenge.rest.client.domain.response.WeatherResponse;
import com.leandromaro.challenge.rest.client.domain.response.darkSky.DarkSkyResponse;
import com.leandromaro.challenge.rest.domain.response.BeerQuantityResponse;
import com.leandromaro.challenge.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.challenge.rest.helper.UrlHelper;
import com.leandromaro.challenge.rest.persistence.domain.MeetUp;
import com.leandromaro.challenge.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpRepository;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpUsersRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_HOST = "community-open-weather-map.p.rapidapi.com";
    private static final String OPEN_WEATHER_KEY = "hw5LL92VcdmshXW5REMeQyiBrO8np1hfjKUjsnYwH0r6ddFEcp";
    private static final String DARK_SKY_HOST = "dark-sky.p.rapidapi.com";
    private static final String DARK_SKY_KEY = "hw5LL92VcdmshXW5REMeQyiBrO8np1hfjKUjsnYwH0r6ddFEcp";

    private final RestTemplate restTemplate;

    private final MeetUpRepository meetUpRepository;

    private final MeetUpUsersRepository meetUpUsersRepository;

    public WeatherService(RestTemplateBuilder builder,
                          MeetUpRepository meetUpRepository,
                          MeetUpUsersRepository meetUpUsersRepository) {
        this.restTemplate = builder.build();
        this.meetUpRepository = meetUpRepository;
        this.meetUpUsersRepository = meetUpUsersRepository;
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


    public BeerQuantityResponse getMeetUpBeerQuantity(long meetUpId){
        DarkSkyResponse weather = this.getWeather(meetUpId);
        double fahrenheitTemperature = weather.getCurrently().getTemperature();
        double celsiusTemperature = (fahrenheitTemperature-32)*(0.5556);

        int enrolledMeetUpUsersQuantity = getEnrolledMeetUpUsersQuantity(meetUpId);

        double beerPerPerson = getNumberOfBeerPerPerson(celsiusTemperature);
        double amountOfBeer = Math.ceil(beerPerPerson * enrolledMeetUpUsersQuantity);
        double amountOfBeerBoxes = getAmountOfBeerBoxes(amountOfBeer);

        return new BeerQuantityResponse((int) amountOfBeer, (int) amountOfBeerBoxes,Double.toString(celsiusTemperature));
    }

    private double getAmountOfBeerBoxes(double amountOfBeer) {
        if (amountOfBeer > 6){
            amountOfBeer =  amountOfBeer / 6;
            return Math.ceil(amountOfBeer);
        }
        return 1;
    }

    private double getNumberOfBeerPerPerson(double celsiusTemperature){
        if(celsiusTemperature < 20){
            return 0.75;
        }else if(celsiusTemperature <=24) {
            return 1;
        }
        return 3;
    }

    private int getEnrolledMeetUpUsersQuantity(long meetUpId) {
        List<MeetUpUsers> enrolledMeetUpUsers = meetUpUsersRepository
                .findAll()
                .stream()
                .filter(meetUpUsers -> meetUpUsers.getMeetUp().getId() == meetUpId)
                .collect(Collectors.toList());
        if (enrolledMeetUpUsers.isEmpty()){
            throw new MeetUpNotFoundException("Not Enrolled Users to the given meetUp");
        }
        return enrolledMeetUpUsers.size();
    }

    private MeetUp getMeetUp(long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        return meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));
    }
}
