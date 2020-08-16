package com.leandromaro.santander.rest.persistence.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MeetUpUsers {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_up_id")
    private MeetUp meetUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserMeetUp user;

    private Date userMeetUpDate;

    private boolean userAttented;
}
