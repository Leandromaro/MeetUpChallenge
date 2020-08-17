package com.leandromaro.santander.rest;

import com.leandromaro.santander.rest.controller.UserMeetUpController;
import com.leandromaro.santander.rest.domain.request.UserMeetUpRequest;
import com.leandromaro.santander.rest.domain.response.UserMeetUpResponse;
import com.leandromaro.santander.rest.service.UserMeetUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    UserMeetUpController userMeetUpController;

    @Mock
    UserMeetUpService userMeetUpService;

    @Test
    public void userControllerMustReturnNotNullResponseEntityResponseTest(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        ResponseEntity<UserMeetUpResponse> userMeetUp = userMeetUpController.createMeetUp(userMeetUpRequest);
        assertNotNull(userMeetUp);
    }

    @Test
    public void userControllerMustReturnResponseEntityResponseTest(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        ResponseEntity<UserMeetUpResponse> userMeetUp = userMeetUpController.createMeetUp(userMeetUpRequest);
        assertEquals("Response must be ResponseEntity ",userMeetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void userControllerMustReturnNotNullBodyTest(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        ResponseEntity<UserMeetUpResponse> userMeetUp = userMeetUpController.createMeetUp(userMeetUpRequest);
        assertNotNull(userMeetUp.getBody());
    }

    @Test
    public void userControllerMustReturnUserMeetUpResponseBodyTest(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        ResponseEntity<UserMeetUpResponse> userMeetUp = userMeetUpController.createMeetUp(userMeetUpRequest);
        assertEquals("Response body must be UserMeetUpResponse",userMeetUp.getBody().getClass(), UserMeetUpResponse.class);
    }

    @Test
    public void userControllerMustReturnResponseEntityResponseStatusCode200Test(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        ResponseEntity<UserMeetUpResponse> userMeetUp = userMeetUpController.createMeetUp(userMeetUpRequest);
        assertTrue("Response must be ResponseEntity ",userMeetUp.getStatusCode().is2xxSuccessful());
    }
}
