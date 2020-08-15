package com.leandromaro.santander.security.mapper;

import com.leandromaro.santander.security.domain.User;
import com.leandromaro.santander.security.rest.domain.request.AuthorizationRequest;
import com.leandromaro.santander.security.rest.domain.response.UserResponse;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(User user) {
		return UserResponse.builder().name(user.getName()).id(user.getId()).build();
	}

	public static User toDomain(AuthorizationRequest authorizationRequest) {
		return User.builder().name(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
				.build();
	}
}
