package com.leandromaro.santander.rest.service;

import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.respository.MeetUpRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetUpService {
    private final MeetUpRepository meetUpRepository;


    MeetUpService(MeetUpRepository meetUpRepository) {
        this.meetUpRepository = meetUpRepository;
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
}
