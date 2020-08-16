package com.leandromaro.santander.rest.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeetUp {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meetUp")
	@JsonIgnore
	private Set<MeetUpUsers> meetUpUser;

	private LocalDate meetUpdate;

	private String address;

	private String name;

	private String city;
}
