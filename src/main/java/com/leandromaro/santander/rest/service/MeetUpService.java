package com.leandromaro.santander.rest.service;

import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.exceptions.UserNotFoundException;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.santander.rest.persistence.domain.UserMeetUp;
import com.leandromaro.santander.rest.persistence.respository.MeetUpRepository;
import com.leandromaro.santander.rest.persistence.respository.MeetUpUsersRepository;
import com.leandromaro.santander.rest.persistence.respository.UserMeetUpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetUpService {

    private final MeetUpRepository meetUpRepository;

    private final UserMeetUpRepository userMeetUpRepository;

    private final MeetUpUsersRepository meetUpUsersRepository;

    MeetUpService(MeetUpRepository meetUpRepository,
                  UserMeetUpRepository userMeetUpRepository,
                  MeetUpUsersRepository meetUpUsersRepository) {
        this.meetUpRepository = meetUpRepository;
        this.userMeetUpRepository = userMeetUpRepository;
        this.meetUpUsersRepository = meetUpUsersRepository;
    }


    public MeetUpResponse createMeetUp(MeetUpRequest meetUpRequest){
        MeetUp meet = MeetUp.builder()
                .address(meetUpRequest.getAddress())
                .meetUpdate(meetUpRequest.getDate())
                .name(meetUpRequest.getName())
                .build();

        MeetUp meetUp = meetUpRepository.save(meet);
        return new MeetUpResponse(meetUp.getId(), meetUp.getName(),meetUp.getAddress());
    }

    public List<MeetUpResponse> allMeetUps(){
        List<MeetUp> all = meetUpRepository.findAll();
        return all.stream()
                .map(meetUp -> new MeetUpResponse(meetUp.getId(),meetUp.getName(),meetUp.getAddress()))
                .collect(Collectors.toList());
    }

    public void enrollUserToMeetUp(long meetUpId,
                                   long userId){
        Optional<UserMeetUp> userMeetUp = userMeetUpRepository.findById(userId);
        UserMeetUp userFound = userMeetUp.orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        MeetUp meetFound = meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));

        MeetUpUsers meetUpUsers = MeetUpUsers.builder()
                .meetUp(meetFound)
                .user(userFound)
                .build();
        meetUpUsersRepository.save(meetUpUsers);
    }
}
