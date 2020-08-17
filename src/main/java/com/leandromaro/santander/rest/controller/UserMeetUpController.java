package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.domain.request.UserMeetUpRequest;
import com.leandromaro.santander.rest.domain.response.UserMeetUpResponse;
import com.leandromaro.santander.rest.service.UserMeetUpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.*;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@RestController
@RequestMapping("/meetUp/user")
@CrossOrigin
public class UserMeetUpController {

    private final UserMeetUpService userMeetUpService;


    UserMeetUpController(UserMeetUpService userMeetUpService) {
        this.userMeetUpService = userMeetUpService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create new MeetUp User", authorizations = { @Authorization(value="jwtToken")})
    @ApiResponses(value = { @ApiResponse(code = SC_CREATED, message = "created"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserMeetUpResponse> createUser(@Valid @RequestBody UserMeetUpRequest userMeetUpRequest) {
        UserMeetUpResponse userMeetUpResponse = userMeetUpService.storeUser(userMeetUpRequest);
        if(isNull(userMeetUpResponse)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userMeetUpResponse, HttpStatus.CREATED);
    }
}
