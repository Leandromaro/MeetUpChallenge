package com.leandromaro.santander.rest.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMeetUp {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private Set<MeetUpUsers> meetUpUser;

	private String firstName;
	
	private String lastName;

}
