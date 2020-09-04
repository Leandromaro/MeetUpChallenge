package com.leandromaro.challenge.rest.controller;

import com.leandromaro.challenge.rest.domain.response.MeetUpResponse;
import com.leandromaro.challenge.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.challenge.rest.exceptions.UserNotFoundException;
import com.leandromaro.challenge.rest.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class NotificationControllerTest {
    @InjectMocks
    NotificationController notificationController;

    @Mock
    NotificationService notificationService;

    @Test
    public void notificationControllerShouldReturn202StatusCodeWhenNotifyUserToMeetUpTest() {
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l);
        assertTrue("Response status must be family 200 ",meetUpResponseResponseEntity.getStatusCode().equals(HttpStatus.ACCEPTED));
    }

    @Test
    public void notificationControllerIs200FamilyStatusCodeWhenNotifyUserToMeetUpTest() {
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l);
        assertTrue("Response status must be family 200 ",meetUpResponseResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void notificationControllerShouldThrowMeetUpNotFoundExceptionTest(){
        doThrow(new MeetUpNotFoundException("MeetUp Not Found")).when(notificationService).notifyUserToMeetUp(anyLong());
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l);
    }


    //
    @Test
    public void notificationControllerShouldReturn202StatusCodeWhenNotifyUserToMeetUpWithUserIdTest() {
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l,1l);
        assertTrue("Response status must be family 200 ",meetUpResponseResponseEntity.getStatusCode().equals(HttpStatus.ACCEPTED));
    }

    @Test
    public void notificationControllerIs200FamilyStatusCodeWhenNotifyUserToMeetUpWithUserIdTest() {
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l,1l);
        assertTrue("Response status must be family 200 ",meetUpResponseResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void notificationControllerShouldThrowMeetUpNotFoundExceptionWithUserIdTest(){
        doThrow(new MeetUpNotFoundException("MeetUp Not Found")).when(notificationService).notifyUserToMeetUp(anyLong(),anyLong());
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l,1l);
    }

    @Test(expected = UserNotFoundException.class)
    public void notificationControllerShouldThrowUserNotFoundExceptionWithUserIdTest(){
        doThrow(new UserNotFoundException("User Not Found")).when(notificationService).notifyUserToMeetUp(anyLong(),anyLong());
        ResponseEntity<MeetUpResponse> meetUpResponseResponseEntity = notificationController.notifyUserToMeetUp(1l,1l);
    }
}
