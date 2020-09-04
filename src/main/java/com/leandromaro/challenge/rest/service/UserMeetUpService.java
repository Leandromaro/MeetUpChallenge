package com.leandromaro.challenge.rest.service;

import com.leandromaro.challenge.rest.domain.request.UserMeetUpRequest;
import com.leandromaro.challenge.rest.domain.response.UserMeetUpResponse;
import com.leandromaro.challenge.rest.persistence.domain.UserMeetUp;
import com.leandromaro.challenge.rest.persistence.respository.UserMeetUpRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMeetUpService {
    private final UserMeetUpRepository userMeetUpRepository;

    UserMeetUpService(UserMeetUpRepository userMeetUpRepository) {
        this.userMeetUpRepository = userMeetUpRepository;
    }

    public UserMeetUpResponse storeUser(UserMeetUpRequest userRequest){
        UserMeetUp userMeetUp = UserMeetUp.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
        UserMeetUp save = userMeetUpRepository.save(userMeetUp);
        return new UserMeetUpResponse(save.getId());
    }
}
