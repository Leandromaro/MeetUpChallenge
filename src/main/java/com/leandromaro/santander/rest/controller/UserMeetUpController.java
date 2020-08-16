package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.domain.request.UserMeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.domain.response.UserMeetUpResponse;
import com.leandromaro.santander.rest.service.UserMeetUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/meetUp/user")
@CrossOrigin
public class UserMeetUpController {

    private final UserMeetUpService userMeetUpService;


    UserMeetUpController(UserMeetUpService userMeetUpService) {
        this.userMeetUpService = userMeetUpService;
    }

    @PostMapping
    public ResponseEntity<UserMeetUpResponse> createMeetUp(@Valid @RequestBody UserMeetUpRequest userMeetUpRequest) {
        UserMeetUpResponse userMeetUpResponse = userMeetUpService.storeUser(userMeetUpRequest);
        if(isNull(userMeetUpResponse)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userMeetUpResponse, HttpStatus.OK);
    }
}
