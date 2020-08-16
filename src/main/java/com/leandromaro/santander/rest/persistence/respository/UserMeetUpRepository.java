package com.leandromaro.santander.rest.persistence.respository;

import com.leandromaro.santander.rest.persistence.domain.UserMeetUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMeetUpRepository extends CrudRepository<UserMeetUp, Long> {

    List<UserMeetUp> findAll();

    @Query("SELECT u FROM UserMeetUp u where u.userName = ?1")
    UserMeetUp findByUserName(String userName);



}
