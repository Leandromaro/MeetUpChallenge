package com.leandromaro.santander.rest.service;

import com.leandromaro.santander.rest.domain.request.MeetUpRequest;
import com.leandromaro.santander.rest.domain.response.MeetUpResponse;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.exceptions.MeetUpUserEventNotFoundException;
import com.leandromaro.santander.rest.exceptions.UserNotFoundException;
import com.leandromaro.santander.rest.persistence.domain.MeetUp;
import com.leandromaro.santander.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.santander.rest.persistence.domain.UserMeetUp;
import com.leandromaro.santander.rest.persistence.respository.MeetUpRepository;
import com.leandromaro.santander.rest.persistence.respository.MeetUpUsersRepository;
import com.leandromaro.santander.rest.persistence.respository.UserMeetUpRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Predicate;
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
       /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String format = sdf.format(meetUpRequest.getDate());
        */
        MeetUp meet = MeetUp.builder()
                .address(meetUpRequest.getAddress())
                .meetUpdate(meetUpRequest.getDate())
                .name(meetUpRequest.getName())
                .city(meetUpRequest.getCity())
                .build();

        MeetUp meetUp = meetUpRepository.save(meet);
        return new MeetUpResponse(meetUp.getId(), meetUp.getName(),meetUp.getAddress(), meetUp.getCity());
    }

    public List<MeetUpResponse> allMeetUps(){
        List<MeetUp> all = meetUpRepository.findAll();
        return all.stream()
                .map(MeetUpService::create)
                .collect(Collectors.toList());
    }

    public void enrollUserToMeetUp(long meetUpId,
                                   long userId){
        UserMeetUp userFound = getUserMeetUp(userId);

        MeetUp meetFound = getMeetUp(meetUpId);

        MeetUpUsers meetUpUsers = MeetUpUsers.builder()
                .meetUp(meetFound)
                .user(userFound)
                .build();
        meetUpUsersRepository.save(meetUpUsers);
    }

    @Transactional
    public void checkInUserToMeetUp(long meetUpId,
                                   long userId){
        UserMeetUp userFound = getUserMeetUp(userId);

        MeetUp meetFound = getMeetUp(meetUpId);

        MeetUpUsers upUsers = getMeetUpUsers(userFound, meetFound);

        meetUpUsersRepository.activeUser(upUsers.getId());
    }

    private MeetUpUsers getMeetUpUsers(UserMeetUp userFound, MeetUp meetFound) {
        return meetUpUsersRepository.findAll()
                .stream()
                .filter(getMeetUpUsersPredicate(userFound, meetFound))
                .findFirst()
                .orElseThrow(() -> new MeetUpUserEventNotFoundException("Event Not Found"));
    }


    private Predicate<MeetUpUsers> getMeetUpUsersPredicate(UserMeetUp userFound, MeetUp meetFound) {
        return meetUpUsers -> meetUpUsers.getMeetUp().getId().equals(meetFound.getId()) &&
                meetUpUsers.getUser().getId().equals(userFound.getId());
    }

    private MeetUp getMeetUp(long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        return meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));
    }

    private UserMeetUp getUserMeetUp(long userId) {
        Optional<UserMeetUp> userMeetUp = userMeetUpRepository.findById(userId);
        return userMeetUp.orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    private static MeetUpResponse create(MeetUp meetUp) {
        return new MeetUpResponse(meetUp.getId(), meetUp.getName(), meetUp.getAddress(), meetUp.getCity());
    }
}
