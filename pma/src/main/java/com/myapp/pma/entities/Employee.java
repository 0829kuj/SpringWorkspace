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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Employee {
	
	@Id  //기본키를 명시
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Id를 자동생성
	private Long employeeId;
	
	@NotBlank(message="이름을 입력해주세요")
	@Size(min=1, max=20, message="이름은 1에서 20자 사이입니다.")
	private String firstName;
	
	@NotBlank(message="성을 입력해주세요")
	@Size(min=1, max=2, message="성은 1에서 2자 사이입니다.")
	private String lastName;
	
	@Email(message="이메일 양식이 올바르지 않습니다")
	@NotBlank(message="이메일을 입력해주세요")
	private String email;
	
	// N:N 관계에서는 테이블을 만들어 만든 테이블에 id를 넣고 다른 테이블의 id도 입력한다.
	// CascadeType.PERSIST, CascadeType.REMOVE 제거
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)  
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name="employee_id"),
					inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<Project> projects;
	
	// 빈 객체 생성	
	public Employee() {
	}
	
	// id 제외한 객체 생성	
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
