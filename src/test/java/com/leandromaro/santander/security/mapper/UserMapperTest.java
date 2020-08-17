package com.leandromaro.santander.security.mapper;

import com.leandromaro.santander.security.domain.User;
import com.leandromaro.santander.security.rest.domain.request.AuthorizationRequest;
import com.leandromaro.santander.security.rest.domain.response.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

	private static final long USER_ID = 1L;

	private static final String USER_NAME = "USER_NAME";

	@Test
	public void toResponseShouldReturnValidUserResponse() {
		User user = User.builder().id(USER_ID).name(USER_NAME).password("USER_PASSWORD").build();

		UserResponse userResponse = UserMapper.toResponse(user);

		assertThat(userResponse.getId()).isEqualTo(user.getId());
		assertThat(userResponse.getName()).isEqualTo(user.getName());

	}

	@Test
	public void toDomainShouldReturnValidUser() {
		AuthorizationRequest authorizationRequest = AuthorizationRequest.builder().userName(USER_NAME)
				.password("USER_PASSWORD").build();

		User user = UserMapper.toDomain(authorizationRequest);

		assertThat(user.getName()).isEqualTo(authorizationRequest.getUserName());
		assertThat(user.getPassword()).isEqualTo(authorizationRequest.getPassword());

	}

}
