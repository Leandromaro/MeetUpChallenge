package com.leandromaro.santander.security.service;

import com.leandromaro.santander.security.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getUser(long id);

	User save(User user);
}
