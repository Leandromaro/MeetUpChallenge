package com.leandromaro.santander.rest.persistence.repository;

import com.leandromaro.santander.rest.persistence.domain.MeetUpUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MeetUpUsersRepository extends CrudRepository<MeetUpUsers, Long> {
	
	List<MeetUpUsers> findAll();

}
