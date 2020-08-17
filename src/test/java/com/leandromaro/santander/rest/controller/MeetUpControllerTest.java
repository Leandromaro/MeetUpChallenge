package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.client.domain.response.darkSky.DarkSkyResponse;
import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.BeerQuantityResponse;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.exceptions.UserNotFoundException;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.santander.rest.persistence.domain.UserMeetUp;
import com.leandromaro.santander.rest.service.MeetUpService;
import com.leandromaro.santander.rest.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
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

    MeetUp meetUp;

    List allMeetUpsList;


    @Before
    public void setUp() throws Exception {
        meetUpRequest = new MeetUpRequest("test", new Date(), "test", "test");
        meetUpResponse = new MeetUpResponse(1l,"test", "test", "test");
        meetUpUsers = new MeetUpUsers(1l, new MeetUp(), new UserMeetUp(), false);
        meetUp = new MeetUp();
        allMeetUpsList = new ArrayList();
        allMeetUpsList.add(meetUp);
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
    public void enrollUserMeetUpShouldThrowUserNotFoundExceptionTest(){
        doThrow(new UserNotFoundException("User Not Found")).when(meetUpService).enrollUserToMeetUp(anyLong(),anyLong());
        meetUpController.enrollUserToMeetUp(1l, 2l);
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void enrollUserMeetUpShouldThrowMeetUpNotFoundExceptionExceptionTest(){
        doThrow(new MeetUpNotFoundException("MeetUp Not Found")).when(meetUpService).enrollUserToMeetUp(anyLong(),anyLong());
        meetUpController.enrollUserToMeetUp(1l, 2l);
    }

    //checkInUserToMeetUp TEST

    @Test
    public void checkInUserToMeetUpMustReturnNotNullTest(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.checkInUserToMeetUp(1l, 2l);
        assertNotNull(enrollUserToMeetUp);
    }

    @Test
    public void checkInUserToMeetUpMustReturnResponseEntityResponseTest(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.checkInUserToMeetUp(1l, 2l);
        assertEquals("Response must be ResponseEntity ",enrollUserToMeetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void checkInUserToMeetUpMustReturnResponseEntityResponseStatusCode200Test(){
        ResponseEntity<MeetUpResponse> enrollUserToMeetUp = meetUpController.checkInUserToMeetUp(1l, 2l);
        assertTrue("Response status must be family 200 ",enrollUserToMeetUp.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = UserNotFoundException.class)
    public void checkInUserToMeetUpShouldThrowUserNotFoundExceptionTest(){
        doThrow(new UserNotFoundException("User Not Found")).when(meetUpService).checkInUserToMeetUp(anyLong(),anyLong());
        meetUpController.checkInUserToMeetUp(1l, 2l);
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void checkInUserToMeetUpShouldThrowMeetUpNotFoundExceptionExceptionTest(){
        doThrow(new MeetUpNotFoundException("MeetUp Not Found")).when(meetUpService).checkInUserToMeetUp(anyLong(),anyLong());
        meetUpController.checkInUserToMeetUp(1l, 2l);
    }


    //getMeetUpWeatherTest

    @Test
    public void getMeetUpWeatherMustReturnNotNullTest(){
        when(weatherService.getWeather(anyLong())).thenReturn(new DarkSkyResponse());
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
        assertNotNull(weatherMeetUp);
    }

    @Test
    public void getMeetUpWeatherMustReturnResponseEntityResponseTest(){
        when(weatherService.getWeather(anyLong())).thenReturn(new DarkSkyResponse());
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
        assertEquals("Response must be ResponseEntity ",weatherMeetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void getMeetUpWeatherMustReturnNotNullBodyTest(){
        when(weatherService.getWeather(anyLong())).thenReturn(new DarkSkyResponse());
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
        assertNotNull(weatherMeetUp.getBody());
    }

    @Test
    public void getMeetUpWeatherMustReturnDarkSkyResponseResponseBodyTest(){
        when(weatherService.getWeather(anyLong())).thenReturn(new DarkSkyResponse());
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
        assertEquals("Response body must be DarkSkyResponse",weatherMeetUp.getBody().getClass(), DarkSkyResponse.class);
    }

    @Test
    public void getMeetUpWeatherMustReturnDarkSkyResponseResponseStatusCode200Test(){
        when(weatherService.getWeather(anyLong())).thenReturn(new DarkSkyResponse());
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
        assertTrue("Response status must be family 200 ",weatherMeetUp.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void getMeetUpWeatherMustReturnThrowMeetUpNotFoundExceptionTest(){
        when(weatherService.getWeather(anyLong())).thenThrow(new MeetUpNotFoundException("Meet Up not found"));
        ResponseEntity<DarkSkyResponse> weatherMeetUp = meetUpController.getMeetUpWeather(1l);
    }

    //getMeetUpBeerQuantity

    @Test
    public void getMeetUpBeerQuantityMustReturnNotNullTest(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenReturn(new BeerQuantityResponse());
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
        assertNotNull(meetUpBeerQuantity);
    }

    @Test
    public void getMeetUpBeerQuantityReturnBeerQuantityResponseResponseTest(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenReturn(new BeerQuantityResponse());
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
        assertEquals("Response must be ResponseEntity ",meetUpBeerQuantity.getClass(), ResponseEntity.class);
    }

    @Test
    public void getMeetUpBeerQuantityMustReturnNotNullBodyTest(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenReturn(new BeerQuantityResponse());
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
        assertNotNull(meetUpBeerQuantity.getBody());
    }

    @Test
    public void getMeetUpBeerQuantityReturnBeerQuantityResponseBodyTest(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenReturn(new BeerQuantityResponse());
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
        assertEquals("Response body must be BeerQuantityResponse",meetUpBeerQuantity.getBody().getClass(), BeerQuantityResponse.class);
    }

    @Test
    public void getMeetUpBeerQuantityReturnBeerQuantityResponseStatusCode200Test(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenReturn(new BeerQuantityResponse());
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
        assertTrue("Response status must be family 200 ",meetUpBeerQuantity.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MeetUpNotFoundException.class)
    public void getMeetUpBeerQuantityShouldThrowMeetUpNotFoundExceptionTest(){
        when(weatherService.getMeetUpBeerQuantity(anyLong())).thenThrow(new MeetUpNotFoundException("Meet Up not found"));
        ResponseEntity<BeerQuantityResponse> meetUpBeerQuantity = meetUpController.getMeetUpBeerQuantity(1l);
    }

    //getAllMeetUps


    @Test
    public void getAllMeetUpsMustReturnNotNull(){
        when(meetUpService.allMeetUps()).thenReturn(allMeetUpsList);
        ResponseEntity<List<MeetUpResponse>> allMeetUps = meetUpController.getAllMeetUps();
        assertNotNull(allMeetUps);
    }

    @Test
    public void getAllMeetUpsMustReturnResponseEntityResponseTest(){
        ResponseEntity<MeetUpResponse> meetUp = meetUpController.createMeetUp(meetUpRequest);
        assertEquals("Response must be ResponseEntity ",meetUp.getClass(), ResponseEntity.class);
    }

    @Test
    public void getAllMeetUpsMustReturnNotNullBodyTest(){
        when(meetUpService.allMeetUps()).thenReturn(allMeetUpsList);
        ResponseEntity<List<MeetUpResponse>> allMeetUps = meetUpController.getAllMeetUps();
        assertNotNull(allMeetUps.getBody());
    }

    @Test
    public void getAllMeetUpsMustReturnListResponseBodyTest(){
        when(meetUpService.allMeetUps()).thenReturn(allMeetUpsList);
        ResponseEntity<List<MeetUpResponse>> allMeetUps = meetUpController.getAllMeetUps();
        assertEquals("Response body must be List",allMeetUps.getBody().getClass(), ArrayList.class);
    }

    @Test
    public void getAllMeetUpsMustReturnResponseEntityResponseStatusCode200Test(){
        when(meetUpService.allMeetUps()).thenReturn(allMeetUpsList);
        ResponseEntity<List<MeetUpResponse>> allMeetUps = meetUpController.getAllMeetUps();
        assertTrue("Response status must be family 200 ",allMeetUps.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getAllMeetUpsMustReturnResponseEntityResponseStatusCode400Test(){
        when(meetUpService.allMeetUps()).thenReturn(new ArrayList<>());
        ResponseEntity<List<MeetUpResponse>> allMeetUps = meetUpController.getAllMeetUps();
        assertTrue("Response status must be family 400 ",allMeetUps.getStatusCode().is4xxClientError());
    }
}
