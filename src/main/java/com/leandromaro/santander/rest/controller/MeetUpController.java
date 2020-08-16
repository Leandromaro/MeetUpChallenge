package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.client.domain.response.darkSky.DarkSkyResponse;
import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.BeerQuantityResponse;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.service.MeetUpService;
import com.leandromaro.santander.rest.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/meetUp")
@CrossOrigin
public class MeetUpController {

    private final MeetUpService meetUpService;
    private final WeatherService weatherService;

    MeetUpController(MeetUpService meetUpService, WeatherService weatherService) {
        this.meetUpService = meetUpService;
        this.weatherService = weatherService;
    }

    //@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<String> getNormalUser(@PathVariable long id) {
        return new ResponseEntity<>("Hi Others", HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<String> getAdminUser(@PathVariable long id) {
        return new ResponseEntity<>("Hi Admin", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MeetUpResponse> createMeetUp(@RequestBody MeetUpRequest meetUpRequest) {
        MeetUpResponse meetUp = meetUpService.createMeetUp(meetUpRequest);
        if(isNull(meetUp)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(meetUp, HttpStatus.CREATED);
    }

    @PostMapping("/{meetUpId}/users/{userId}")
    public ResponseEntity<MeetUpResponse> addUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        meetUpService.enrollUserToMeetUp(meetUpId,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{meetUpId}/users/{userId}/checkIn")
    public ResponseEntity<MeetUpResponse> checkInUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        meetUpService.checkInUserToMeetUp(meetUpId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{meetUpId}/weather")
    public ResponseEntity<DarkSkyResponse> getMeetUpWeather(
            @PathVariable long meetUpId){
        DarkSkyResponse darkSkyResponse = weatherService.getWeather(meetUpId);
        return new ResponseEntity<>(darkSkyResponse, HttpStatus.OK);
    }

    @GetMapping("/{meetUpId}/beerQuantity")
    public ResponseEntity<BeerQuantityResponse> getMeetUpBeerQuantity(
            @PathVariable long meetUpId){
        BeerQuantityResponse beerQuantity = weatherService.getMeetUpBeerQuantity(meetUpId);
        return new ResponseEntity<>(beerQuantity, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<MeetUpResponse>> getAllMeetUps(){
        List<MeetUpResponse> meetUpResponses = meetUpService.allMeetUps();
        if(meetUpResponses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(meetUpResponses, HttpStatus.OK);
    }

}
