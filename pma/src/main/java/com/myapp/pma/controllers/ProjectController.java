package com.myapp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.pma.entities.Employee;
import com.myapp.pma.entities.Project;
import com.myapp.pma.services.EmployeeService;
import com.myapp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	// 스프링에서 repository 객체를 처음에 자동생성하여 가지고 있다가 
	// Autowired를 만나면 관련 객체가 필요할때 자동으로 연결해준다 
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String diplayProjectList(Model model) {
		List<Project> projectList = projectService.findAll();
		model.addAttribute("projectList", projectList);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project p = new Project();
		model.addAttribute("project", p);
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("empList", empList);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(@Valid Project project, Errors errors, Model model) {
		if (errors.hasErrors()) {
			List<Employee> empList = employeeService.findAll();
			model.addAttribute("empList", empList);		// 프로젝트에 할당된 직원정보도 필요하므로 가져옴 
			return "projects/new-project";
		}
		
		Long id = project.getProjectId();
		System.out.println("받아온 projectId= " + id);
		
		if (id != null) {
			projectService.update(project);
		} else {
			projectService.save(project);
		}
		return "redirect:/projects";	// post-redirect-get 패턴(new > save > new)
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
		// id로 DB에서 업데이트할 프로젝트를 찾아서 화면에 표시하기
		Project project = projectService.findByProjectId(id); // DB에서 찾기
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("project", project);
		model.addAttribute("empList", empList);		// 프로젝트에 할당된 직원정보도 필요하므로 가져옴 
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id) {
		projectService.deleteProjectById(id);
		return "redirect:/projects";
	}
	
}
