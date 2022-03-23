package com.myapp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.pma.dao.ProjectRepository;
import com.myapp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	// 스피링에서 repository 객체를 처음에 자동생성하여 가지고 있다가 Autowired를 만나면 관련 객체가 필요할때 자동으로 연결해줌  
	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project p = new Project();
		model.addAttribute("project", p);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project) {
		projectRepository.save(project);	// project객체를 DB의 테이블에 저장
		return "redirect:/projects/new";	// post-redirect-get 패턴(/new > /save > /new)
	}
}
