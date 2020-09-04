package com.leandromaro.challenge.security.repository;

import com.leandromaro.challenge.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleById(long id);

	Role findByName(String name);
}
