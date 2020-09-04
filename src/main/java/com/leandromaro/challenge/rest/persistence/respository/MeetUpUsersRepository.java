package com.leandromaro.challenge.rest.persistence.respository;

import com.leandromaro.challenge.rest.persistence.domain.MeetUpUsers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface MeetUpUsersRepository extends CrudRepository<MeetUpUsers, Long> {
	
	List<MeetUpUsers> findAll();

	@Modifying
	@Query("update MeetUpUsers m set m.userAttend= true where m.id=?1")
	@Transactional
	void activeUser(Long id);

}
