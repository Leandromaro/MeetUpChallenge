package com.leandromaro.challenge.rest.controller;

import com.leandromaro.challenge.rest.domain.response.MeetUpResponse;
import com.leandromaro.challenge.rest.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static javax.servlet.http.HttpServletResponse.*;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@RestController
@RequestMapping("/notify")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @PostMapping("/meetUps/{meetUpId}")
    @ApiOperation(value = "Notify all Users into a MeetUp", authorizations = { @Authorization(value="jwtToken")})
    @ApiResponses(value = { @ApiResponse(code = SC_ACCEPTED, message = "accepted"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<MeetUpResponse> notifyUserToMeetUp(
            @PathVariable long meetUpId) {
        notificationService.notifyUserToMeetUp(meetUpId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @PostMapping("/meetUps/{meetUpId}/users/{userId}")
    @ApiOperation(value = "Notify a single Users into a MeetUp", authorizations = { @Authorization(value="jwtToken")})
    @ApiResponses(value = { @ApiResponse(code = SC_ACCEPTED, message = "accepted"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred"),
            @ApiResponse(code = SC_FORBIDDEN, message = "Forbidden Access")
    })
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<MeetUpResponse> notifyUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        notificationService.notifyUserToMeetUp(meetUpId, userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
