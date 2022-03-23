package com.myapp.pma.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id		// 기본키를 명시
	@GeneratedValue(strategy = GenerationType.AUTO)	// Id를 자동생성
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	public Employee() {	}

	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getEmplyeeId() {
		return employeeId;
	}

	public void setEmplyeeId(Long emplyeeId) {
		this.employeeId = emplyeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
