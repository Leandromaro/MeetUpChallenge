package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<MeetUpResponse> notifyUserToMeetUp(
            @PathVariable long meetUpId) {
        notificationService.notifyUserToMeetUp(meetUpId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @PostMapping("/meetUps/{meetUpId}/users/{userId}")
    public ResponseEntity<MeetUpResponse> notifyUserToMeetUp(
            @PathVariable long meetUpId,
            @PathVariable long userId) {
        notificationService.notifyUserToMeetUp(meetUpId, userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
