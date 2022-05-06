package com.myapp.maybeCafe.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private int uno;				// 회원번호
	private String uid;				// 유저id 
	private String password;		// password
	private String email;			// 이메일
	private String username;		// 유저이름
	private String phone_number;	// 폰번호 
	private String profile_img;		// 프로필사진
	private String profile_description;	// 프로필소개
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 목록을 리턴
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되었는가?
		return false;	// 만료안됨
	}
	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있는가?
		return false;	// 잠기지 않음
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되었는가?
		return false;	// 만료안됨
	}
	@Override
	public boolean isEnabled() {
		// 사용가능한 계저인가?
		return false;	// 사용가능
	}

}