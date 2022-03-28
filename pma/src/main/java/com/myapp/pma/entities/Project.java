package com.myapp.pma.entities;

import java.util.ArrayList;
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
public class Project {

	@Id // Id가 기본키임을 알림
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id를 자동으로 생성할것을 알림
	private Long projectId; // 프로젝트 아이디 (CamelCase => DB project_id)

	private String name; // 프로젝트 이름
	private String stage; // 프로젝트 상태 (시작전, 진행중, 완료)
	private String description; // 프로젝트 설명

	// CascadeType.PERSIST, CascadeType.REMOVE 제거
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;

	public Project() {
	}

	public Project(String name, String stage, String description) {
		// id는 DB 자동생성예정이므로 제외
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", stage=" + stage + ", description="
				+ description + "]";
	}

	// 프로젝트 객체에서 직원을 더하는 메서드. employees가 null이면 새 ArrayList를 만들어 매개변수로 받은 객체(직원)를 리스트에 추가한다.
	public void addEmployee(Employee emp) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}

}
