package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.client.domain.response.darkSky.DarkSkyResponse;
import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.BeerQuantityResponse;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.service.MeetUpService;
import com.leandromaro.santander.rest.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/meetUps")
@CrossOrigin
public class MeetUpController {

    private final MeetUpService meetUpService;
    private final WeatherService weatherService;

    MeetUpController(MeetUpService meetUpService, WeatherService weatherService) {
        this.meetUpService = meetUpService;
        this.weatherService = weatherService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Creates MeetUps")
    @ApiResponses(value = { @ApiResponse(code = SC_CREATED, message = "created"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MeetUpResponse> createMeetUp(@RequestBody MeetUpRequest meetUpRequest) {
        MeetUpResponse meetUp = meetUpService.createMeetUp(meetUpRequest);
        if(isNull(meetUp)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(meetUp, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{meetUpId}/users/{userId}")
    @ApiOperation(value = "Enroll User To MeetUp")
    @ApiResponses(value = { @ApiResponse(code = SC_CREATED, message = "created"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<MeetUpResponse> enrollUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        meetUpService.enrollUserToMeetUp(meetUpId,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{meetUpId}/users/{userId}/checkIn")
    @ApiOperation(value = "Check in User To MeetUp")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    public ResponseEntity<MeetUpResponse> checkInUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        meetUpService.checkInUserToMeetUp(meetUpId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{meetUpId}/weather")
    @ApiOperation(value = "Get MeetUp Weather")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    public ResponseEntity<DarkSkyResponse> getMeetUpWeather(
            @PathVariable long meetUpId){
        DarkSkyResponse darkSkyResponse = weatherService.getWeather(meetUpId);
        return new ResponseEntity<>(darkSkyResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{meetUpId}/beerQuantity")
    @ApiOperation(value = "Get number of beer to buy")
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    public ResponseEntity<BeerQuantityResponse> getMeetUpBeerQuantity(
            @PathVariable long meetUpId){
        BeerQuantityResponse beerQuantity = weatherService.getMeetUpBeerQuantity(meetUpId);
        return new ResponseEntity<>(beerQuantity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping
    @ApiOperation(value = "Get all the registered MeetUps", authorizations = { @Authorization(value="jwtToken")})
    @ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    public ResponseEntity<List<MeetUpResponse>> getAllMeetUps(){
        List<MeetUpResponse> meetUpResponses = meetUpService.allMeetUps();
        if(meetUpResponses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(meetUpResponses, HttpStatus.OK);
    }

}
