package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.request.UserMeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.domain.response.UserMeetUpResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.exceptions.UserNotFoundException;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.santander.rest.persistence.domain.UserMeetUp;
import com.leandromaro.santander.rest.service.MeetUpService;
import com.leandromaro.santander.rest.service.UserMeetUpService;
import com.leandromaro.santander.rest.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetUpControllerTest {

    @InjectMocks
    MeetUpController meetUpController;

    @Mock
    MeetUpService meetUpService;

    @Mock
    WeatherService weatherService;

    MeetUpRequest meetUpRequest;

    MeetUpResponse meetUpResponse;

    MeetUpUsers meetUpUsers;


    @Before
    public void setUp() throws Exception {
        meetUpRequest = new MeetUpRequest("test", new Date(), "test", "test");
        meetUpResponse = new MeetUpResponse(1l,"test", "test", "test");
        meetUpUsers = new MeetUpUsers(1l, new MeetUp(), new UserMeetUp(), false);
    }

    // Create MeetUp Test

    @Test
    public void createMeetUpMustReturnNotNullTest(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(meetUpResponse);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertNotNull(meetUp);
    }

    @Test
    public void meetUpControllerMustReturnResponseEntityResponseTest(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(meetUpResponse);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertEquals("Response must be ResponseEntity ",meetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void meetUpControllerMustReturnNotNullBodyTest(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(meetUpResponse);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertNotNull(meetUp.getBody());
    }

    @Test
    public void meetUpControllerMustReturnUserMeetUpResponseBodyTest(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(meetUpResponse);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertEquals("Response body must be MeetUpResponse",meetUp.getBody().getClass(), MeetUpResponse.class);
    }

    @Test
    public void meetUpControllerMustReturnResponseEntityResponseStatusCode200Test(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(meetUpResponse);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertTrue("Response status must be family 200 ",meetUp.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void meetUpControllerMustReturnResponseEntityResponseStatusCode400Test(){
        when(meetUpService.createMeetUp(meetUpRequest)).thenReturn(null);
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertTrue("Response status must be family 400 ",meetUp.getStatusCode().is4xxClientError());
    }

    // EnrollUserMeetUp Test

    @Test
    public void enrollUserMeetUpMustReturnNotNullTest(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.enrollUserToMeetUp(1l, 2l);
        assertNotNull(enrollUserToMeetUp);
    }

    @Test
    public void enrollUserMeetUpMustReturnResponseEntityResponseTest(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.enrollUserToMeetUp(1l, 2l);
        assertEquals("Response must be ResponseEntity ",enrollUserToMeetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void enrollUserMeetUpMustReturnResponseEntityResponseStatusCode200Test(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.enrollUserToMeetUp(1l, 2l);
        assertTrue("Response status must be family 200 ",enrollUserToMeetUp.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = UserNotFoundException.class)
    public void enrollUserMeetUpMustShouldThrowUserNotFoundExceptionTest(){
        doThrow(new UserNotFoundException("User Not Found")).when(meetUpService).enrollUserToMeetUp(anyLong(),anyLong());
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.enrollUserToMeetUp(1l, 2l);
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void enrollUserMeetUpMustShouldThrowMeetUpNotFoundExceptionExceptionTest(){
        doThrow(new MeetUpNotFoundException("MeetUp Not Found")).when(meetUpService).enrollUserToMeetUp(anyLong(),anyLong());
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.enrollUserToMeetUp(1l, 2l);
    }

}
