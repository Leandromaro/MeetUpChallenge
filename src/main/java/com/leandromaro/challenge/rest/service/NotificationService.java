package com.leandromaro.challenge.rest.service;

import com.leandromaro.challenge.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.challenge.rest.exceptions.UserNotFoundException;
import com.leandromaro.challenge.rest.persistence.domain.MeetUp;
import com.leandromaro.challenge.rest.persistence.domain.MeetUpUsers;
import com.leandromaro.challenge.rest.persistence.domain.UserMeetUp;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpRepository;
import com.leandromaro.challenge.rest.persistence.respository.MeetUpUsersRepository;
import com.leandromaro.challenge.rest.persistence.respository.UserMeetUpRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class NotificationService {

    private final MeetUpRepository meetUpRepository;

    private final UserMeetUpRepository userMeetUpRepository;

    private final MeetUpUsersRepository meetUpUsersRepository;

    NotificationService(MeetUpRepository meetUpRepository,
                  UserMeetUpRepository userMeetUpRepository,
                  MeetUpUsersRepository meetUpUsersRepository) {
        this.meetUpRepository = meetUpRepository;
        this.userMeetUpRepository = userMeetUpRepository;
        this.meetUpUsersRepository = meetUpUsersRepository;
    }

    public void notifyUserToMeetUp(long meetUpId){
        MeetUp meetFound = getMeetUp(meetUpId);
        meetUpUsersRepository.findAll()
                .stream()
                .filter(meetUpUsers -> meetFound.getId().equals(meetUpUsers.getMeetUp().getId()))
                .filter(meetUpUsers -> nonNull(meetUpUsers.getUser()))
                .forEach(this::notify);
    }

    public void notifyUserToMeetUp(long meetUpId, long userId){
        MeetUp meetFound = getMeetUp(meetUpId);

        UserMeetUp userFound = getUserMeetUp(userId);

        notify(meetFound,userFound);
    }

    private void notify(MeetUpUsers meetUpUsers) {
        String email = meetUpUsers.getUser().getEmail();
        String mobileNumber = meetUpUsers.getUser().getMobileNumber();
        if(nonNull(email) && nonNull(mobileNumber)){
            System.out.println("Email and Mobile number must be send to a notification API");
        }
    }

    private void notify(MeetUp meetFound,UserMeetUp userMeetUp) {
        String email = userMeetUp.getEmail();
        String mobileNumber = userMeetUp.getMobileNumber();
        if(nonNull(email) && nonNull(mobileNumber) && nonNull(meetFound.getName())){
            System.out.println("Email and Mobile number must be send to a notification API");
        }
    }

    private MeetUp getMeetUp(long meetUpId) {
        Optional<MeetUp> meetUp = meetUpRepository.findById(meetUpId);
        return meetUp.orElseThrow(() -> new MeetUpNotFoundException("Meet Up not found"));
    }

    private UserMeetUp getUserMeetUp(long userId) {
        Optional<UserMeetUp> userMeetUp = userMeetUpRepository.findById(userId);
        return userMeetUp.orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }
}
