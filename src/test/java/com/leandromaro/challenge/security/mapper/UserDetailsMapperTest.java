package com.leandromaro.challenge.security.mapper;

import com.leandromaro.challenge.security.domain.Role;
import com.leandromaro.challenge.security.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsMapperTest {

	private User user;

	@Before
	public void initData() {
		Set<Role> roles = new HashSet<>();
		roles.add(Role.builder().id(1L).name("ADMIN").description("ADMINROLE").build());
		roles.add(Role.builder().id(1L).name("USER").description("USERROLE").build());

		user = User.builder().id(1L).name("USERNAME").password("PASSWORD").roles(roles).build();
	}

	@Test
	public void buildUserDetailsShouldConvertFromUser() {
		List<String> expectedAuthorities = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
		UserDetails userDetails = UserDetailsMapper.build(user);

		assertThat(userDetails).isNotNull();
		assertThat(userDetails.getUsername()).isEqualTo(user.getName());
		assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
		assertThat(userDetails.getAuthorities()).isNotEmpty();
		assertThat(userDetails.getAuthorities()).hasSize(2);

		final List<String> userAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		assertThat(userAuthorities).containsAll(expectedAuthorities);

	}

}
