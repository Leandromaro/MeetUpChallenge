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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    UserMeetUpController userMeetUpController;

    @Mock
    UserMeetUpService userMeetUpService;

    @Test
    public void test(){
        when(userMeetUpService.storeUser(any())).thenReturn(new UserMeetUpResponse(1l));
        UserMeetUpRequest userMeetUpRequest = new UserMeetUpRequest("test", "test");
        userMeetUpController.createMeetUp(userMeetUpRequest);
    }
}
