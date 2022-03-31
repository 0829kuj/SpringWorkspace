package com.myapp.pma.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.pma.dao.ProjectRepository;
import com.myapp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public void save(Project project) {
		projectRepository.save(project);
	}

	public Project findByProjectId(long id) {
		// id를 가지고 DB에서 해당 프로젝트를 찾음
		return projectRepository.findByProjectId(id);
	}	
	
	public void update(Project project) {
		// 실제 DB에서 업데이트할 프로젝트객체를 불러옴
		Project pro = projectRepository.findByProjectId(project.getProjectId());
		// 필요한 내용 업데이트
		pro.setName(project.getName());
		pro.setStage(project.getStage());
		pro.setDescription(project.getDescription());
		projectRepository.save(pro);	// 일부 값이 수정된 pro객체를 DB에 저장
	}

	public void deleteProjectById(long id) {
		// 선태한 프로젝트를 삭제하는 메서드
		projectRepository.deleteById(id);
	}

}
