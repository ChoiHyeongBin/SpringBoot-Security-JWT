package com.chb.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		// session 을 사용하지 않음
		.and()
		.formLogin().disable()		// form 로그인 사용안함 (JWT 서버이기 때문에 form 으로 ID, PW 로그인을 하지 않음, JWT 사용시 고정(코드 11~14번째 줄))
		.httpBasic().disable()
		.authorizeRequests()
		.antMatchers("/api/v1/user/**")		// 이 주소로 들어오면
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")		// 이 역할을 부여받은 사람만 접속가능
		.antMatchers("/api/v1/manager/**")	// manager 로 들어오면
		.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")	// manager 와 admin 만 들어올 수 있음
		.antMatchers("/api/v1/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();		// 다른 요청은 전부 다 권한없이 들어갈 수 있음
	}
	
}
