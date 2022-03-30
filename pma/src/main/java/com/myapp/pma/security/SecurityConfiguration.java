package com.myapp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 시큐리티 설정을 위해서는 1) WebSecurityConfigurerAdapter를 상속받아야하며 2) 어노테이션 EnableWebSecurity가 필요  
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;	// DB객체
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // 패스워드 인코딩 객체
	
	// 3) 인증	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// DB에서 가져온 유저, 비밀번호, roles(허용범위 = 유저, 관리자)을 검사함
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username,password,enabled from user_accounts where username = ? ")	// username과 password를 검색
			.authoritiesByUsernameQuery("select username, role from user_accounts where username = ? ")			// 해당 username에 대한 role을 검색
			.dataSource(dataSource)		// DB에서 가져온 데이터를 사용
			.passwordEncoder(bCryptPasswordEncoder);	// 패스워드 인코딩 객체를 통해 검사. DB에 저장된 암호화 된 패스워드를 다시 디코딩 하는 기능도 포함함.
	}
	
	// 4) 허가
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.authorizeRequests()
			.antMatchers("/projects/new").hasRole("ADMIN")		// 관리자의 허용범위 지정(새 프로젝트, 새 직원 추가 가능)
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasRole("ADMIN") 
			.antMatchers("/employees/save").hasRole("ADMIN")
			.antMatchers("/employees").authenticated()			// 인증된 유저(로그인 한 모든 유저)에게만 허용
			.antMatchers("/projects").authenticated()
			.antMatchers("/","/**").permitAll()						// 그 외 페이지는 인증과 관계없이 모두에게 열람허용
			.and().formLogin(form -> form.loginPage("/login").permitAll())	// 커스텀 로그인 페이지 추가
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); 	// 로그아웃 추가
//			.and().exceptionHandling().accessDeniedPage("/"); //예외 발생시 기본페이지로
		// 시큐리티에서는 기본적으로 csrf방지가 적용됨
//		http.csrf().disable();	
		// csrf룰(사용자가 의도치않은 요청)에 걸리는 부분에 대해 막아줌(/save실행시 redirect로 요청을 보내게 되어있기때문) 												
	}
}
