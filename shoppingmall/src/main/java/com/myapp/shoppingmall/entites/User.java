package com.myapp.shoppingmall.entites;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name="users")	// DB의 users테이블과 매핑
@Data
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// DB서 자동생성
	private int id;
	
	@NotBlank
	@Size(min = 2, message = "유저이름은 2자 이상")
	private String username;

	@NotBlank
	@Size(min = 4, message = "패스워드는 4자 이상")
	private String password;
	
	@Transient		// 실제 테이블에는 없고 패스워드 확인 시 사용
	private String confirmPassword;
	
	@Email(message = "이메일 양식에 맞게 입력하시오")
	private String email;
	
	@Column(name = "phone_number")	// DB의 테이블과 이름이 다르므로 설정해줌
	@Size(min = 6, message = "전화번호는 6자리 이상")
	private String phoneNumber;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 목록을 리턴 (user 권한)
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
