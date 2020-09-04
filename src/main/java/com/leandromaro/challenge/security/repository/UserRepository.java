package com.leandromaro.challenge.security.repository;

import com.leandromaro.challenge.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findById(long id);

	List<User> findAll();
}
