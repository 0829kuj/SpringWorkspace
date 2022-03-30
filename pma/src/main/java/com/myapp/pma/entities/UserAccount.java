package com.myapp.pma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity		// 테이블과 매핑되는 클래스임을 알림
@Table(name= "userAccounts")		// 매핑할 DB의 테이블명과 클래스의 이름이 다를경우 name속성을 통해 지정
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// DB에서 자동생성(AUTO_INCREMENT)하도록 설정
	@Column(name= "user_id")		// 테이블의 column명과 다르므로 name속성을 통해 매핑시킴
	private long userId;
	
	@Column(name= "username")
	private String userName;
	
	private String email;
	private String password;
	private String role;
	private Boolean enabled;
	
	// 빈 유저 객체 생성 시 role은 user, enable은 ture로 설정
	public UserAccount() {
		this.role = "ROLE_USER";
		this.enabled = true;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
