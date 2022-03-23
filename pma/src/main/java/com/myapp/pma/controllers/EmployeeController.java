package com.myapp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.entities.Employee;
import com.myapp.pma.entities.Project;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired		// db에서 project테이블을 가져오기 위함 
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/")
	public String displayEmployeeForm(Model model) {
		List<Employee> employeeList = employeeRepository.findAll();
		model.addAttribute("employeeList", employeeList);
		return "employees/employeeList";
	}
	
	@GetMapping("/new")
	public String newEmployeeForm(Model model) {
		Employee e = new Employee(); 
		model.addAttribute("employee", e);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/employees/";
	}
}
