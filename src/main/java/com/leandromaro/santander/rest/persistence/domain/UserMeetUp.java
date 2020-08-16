package com.leandromaro.santander.rest.persistence.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserMeetUp {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private Set<MeetUpUsers> meetUpUser;

	private String firstName;
	
	private String userName;
	
	private String lastName;
	
	private String password;

}
