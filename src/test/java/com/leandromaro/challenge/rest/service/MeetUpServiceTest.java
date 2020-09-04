package com.leandromaro.challenge.rest.service;

import com.leandromaro.challenge.rest.domain.request.MeetUpRequest;
import com.leandromaro.challenge.rest.domain.response.MeetUpResponse;
import com.leandromaro.challenge.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.challenge.rest.exceptions.MeetUpUserEventNotFoundException;
import com.leandromaro.challenge.rest.exceptions.UserNotFoundException;
import com.leandromaro.challenge.rest.persistence.domain.MeetUp;
import com.leandromaro.challenge.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.challenge.rest.persistence.domain.UserMeetUp;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpRepository;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpUsersRepository;
import com.leandromaro.challenge.rest.persistence.respository.UserMeetUpRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetUpServiceTest {
    @InjectMocks
    MeetUpService meetUpService;

    @Mock
    MeetUpRepository meetUpRepository;

    @Mock
    UserMeetUpRepository userMeetUpRepository;

    @Mock
    MeetUpUsersRepository meetUpUsersRepository;

    MeetUpRequest meetUpRequest;

    MeetUp meetUp;

    ArrayList<MeetUp> meetUps;

    Set<MeetUpUsers> usersSet = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        meetUpRequest = new MeetUpRequest("test", new Date(), "test", "test");
        usersSet = new HashSet<>();
        usersSet.add(new MeetUpUsers(1l,MeetUp.builder().id(1l).build(), UserMeetUp.builder().id(1l).build(), false));
        meetUp = MeetUp
                .builder()
                .id(1l)
                .name("test")
                .address("test")
                .city("test")
                .meetUpUser(usersSet)
                .meetUpdate(new Date())
                .build();
        meetUps = new ArrayList<>();
        meetUps.add(meetUp);
    }

    @Test
    public void createMeetUpShouldReturnNotNullTest(){
        when(meetUpRepository.save(Mockito.any())).thenReturn(new MeetUp());
        MeetUpResponse meetUp = meetUpService.createMeetUp(meetUpRequest);
        assertNotNull(meetUp);
    }

    @Test
    public void createMeetUpShouldBeMeetUpClassTest(){
        when(meetUpRepository.save(Mockito.any())).thenReturn(new MeetUp());
        MeetUpResponse meetUp = meetUpService.createMeetUp(meetUpRequest);
        assertEquals("MeetUp must be MeetUpResponse",meetUp.getClass(), MeetUpResponse.class);
    }

    //allMeetUps

    @Test
    public void allMeetUpsMustReturnNotEmptyTest() {
        when(meetUpRepository.findAll()).thenReturn(meetUps);
        List<MeetUpResponse> meetUpResponses = meetUpService.allMeetUps();
        assertFalse("Response must not be empty",meetUpResponses.isEmpty());
    }

    @Test
    public void allMeetUpsResponseMustBeMeetUpResponseClassTest() {
        when(meetUpRepository.findAll()).thenReturn(meetUps);
        List<MeetUpResponse> meetUpResponses = meetUpService.allMeetUps();
        assertEquals("Response Class must be MeetUpResponse",MeetUpResponse.class,meetUpResponses.stream().findAny().get().getClass());
    }

    //enrollUserToMeetUp

    @Test
    public void enrollUserMeetUpMustShouldNotThrowExceptionTest(){
        when(userMeetUpRepository.findById(anyLong())).thenReturn(Optional.of(new UserMeetUp()));
        when(meetUpRepository.findById(anyLong())).thenReturn(Optional.of(new MeetUp()));
        Assertions.assertThatCode(() -> meetUpService.enrollUserToMeetUp(1l, 2l))
                .doesNotThrowAnyException();
    }

    @Test(expected = UserNotFoundException.class)
    public void enrollUserMeetUpMustShouldThrowUserNotFoundExceptionTest(){
        meetUpService.enrollUserToMeetUp(1l, 2l);
    }
    @Test(expected = MeetUpNotFoundException.class)
    public void enrollUserMeetUpMustShouldThrowMeetUpNotFoundExceptionTest(){
        when(userMeetUpRepository.findById(anyLong())).thenReturn(Optional.of(new UserMeetUp()));
        meetUpService.enrollUserToMeetUp(1l, 2l);
    }

    //checkInUserToMeetUp

    @Test(expected = MeetUpUserEventNotFoundException.class)
    public void checkInUserToMeetUpMustShouldThrowMeetUpUserEventNotFoundExceptionTest() {
        when(userMeetUpRepository.findById(anyLong())).thenReturn(Optional.of(new UserMeetUp()));
        when(meetUpRepository.findById(anyLong())).thenReturn(Optional.of(new MeetUp()));
        meetUpService.checkInUserToMeetUp(1,2l);
    }
}
