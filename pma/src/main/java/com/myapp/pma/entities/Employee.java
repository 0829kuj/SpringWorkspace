package com.myapp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
	
	@Id		// 기본키를 명시
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Id를 자동생성
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	// N:N 관계에서는 테이블을 만들어 만든 테이블에 id를 넣고 다른 테이블의 id도 입력한다.
	// CascadeType.PERSIST, CascadeType.REMOVE 제거
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "employee_id"),
				inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<Project> projects;
	
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
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
}
