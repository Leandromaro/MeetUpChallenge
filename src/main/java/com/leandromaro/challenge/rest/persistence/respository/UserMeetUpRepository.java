package com.leandromaro.challenge.rest.persistence.respository;

import com.leandromaro.challenge.rest.persistence.domain.UserMeetUp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMeetUpRepository extends CrudRepository<UserMeetUp, Long> {

    List<UserMeetUp> findAll();
}
