package com.leandromaro.santander.security.config;

import com.leandromaro.santander.security.config.filter.JwtAuthenticationFilter;
import com.leandromaro.santander.security.config.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilterBean() {
		return new JwtAuthorizationFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.cors().and()
				.csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				// ELIMINAR
				.and().authorizeRequests().antMatchers(HttpMethod.POST, "/meetUp").permitAll()
				.and().authorizeRequests().antMatchers(HttpMethod.GET, "/meetUp").permitAll()
				.and().authorizeRequests().antMatchers(HttpMethod.POST, "/meetUp/user").permitAll()
				//
				.anyRequest().authenticated().and()
				.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
