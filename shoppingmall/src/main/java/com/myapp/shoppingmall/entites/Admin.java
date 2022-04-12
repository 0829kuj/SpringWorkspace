package com.myapp.shoppingmall.entites;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name="admin")	// DB의 users테이블과 매핑
@Data
public class Admin implements UserDetails {
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// DB서 자동생성
	private int id;
	
	private String username;

	private String password;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 목록을 리턴 (관리자 권한)
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되었는가?
		return true;	// 만료안됨	
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있는가?
		return true;	// 잠기지 않음
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되었는가?
		return true;	// 만료안됨
	}

	@Override
	public boolean isEnabled() {
		// 사용가능한 계정인가?
		return true;	// 사용가능
	}

}
