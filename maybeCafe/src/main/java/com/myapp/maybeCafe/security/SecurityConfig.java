package com.myapp.maybeCafe.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// 시큐리티는 1.인증(로그인), 2.허가(role에 따른 허용가능범위 설정) 이 가능.

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 허가(role에 따른 허용범위)
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()	// 누구나 허용
				.and()
					.formLogin().loginPage("/login")	// 인증 로그인 페이지 주소
				.and()
					.logout().logoutSuccessUrl("/")		// 로그아웃 후 홈으로 이동
				.and()
					.exceptionHandling().accessDeniedPage("/");	// 예외 발생 시 홈으로 이동
	}
}
