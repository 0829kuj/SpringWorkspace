package com.myapp.pma.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
	
	@Id		// Id가 기본키임을 알림
	@GeneratedValue(strategy = GenerationType.AUTO)	// id를 자동으로 생성할것을 알림
	private Long projectId;		// 프로젝트 아이디	(CamelCase => DB project_id)
	
	private String name;		// 프로젝트 이름
	private String stage;		// 프로젝트 상태 (시작전, 진행중, 완료)
	private String description;	// 프로젝트 설명
	
	public Project() {	}

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

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", stage=" + stage + ", description="
				+ description + "]";
	}
	
}
