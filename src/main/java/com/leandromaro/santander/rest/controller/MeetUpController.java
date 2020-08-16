package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.service.MeetUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/meetUp")
@CrossOrigin
public class MeetUpController {

    private final MeetUpService meetUpService;

    MeetUpController(MeetUpService meetUpService) {
        this.meetUpService = meetUpService;
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
    public ResponseEntity<MeetUpResponse> createMeetUp(@Valid @RequestBody MeetUpRequest meetUpRequest) {
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

    @GetMapping
    public ResponseEntity<List<MeetUpResponse>> getAllMeetUps(){
        List<MeetUpResponse> meetUpResponses = meetUpService.allMeetUps();
        if(meetUpResponses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(meetUpResponses, HttpStatus.OK);
    }

}
