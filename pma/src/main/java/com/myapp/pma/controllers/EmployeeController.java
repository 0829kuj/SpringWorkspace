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
import com.myapp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 직원 리스트를 출력
	 * @param model
	 * @return 직원 리스트 페이지
	 */
	@GetMapping
	public String displayEmployeeForm(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("empList", empList);
		return "employees/list-employees";
	}
	/**
	 * 새 직원을 입력
	 * @param model
	 * @return 직원추가 페이지
	 */
	@GetMapping("/new")
	public String newEmployeeForm(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "employees/new-employee";
	}
	/**
	 * 직원을 저장 or 업데이트
	 * @param employee
	 * @return 직원 리스트 페이지
	 */
	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, Errors errors) {
		if (errors.hasErrors()) return "employees/new-employee";
		
		Long id = employee.getEmployeeId();
		if (id != null) {		// id가 있을 경우 업데이트
			employeeService.update(employee);
		} else {				// id가 없을 경우 새로 생성
			employeeService.save(employee);
		}
		return "redirect:/employees"; 	//post-redirect-get 패턴
	}
	/**
	 * 업데이트 창에 id를 통해 받아온 직원의 정보를 띄움
	 * @param id (employeeId)
	 * @param model
	 * @return 직원 업데이트 페이지 (직원추가 페이지와 동일. 값을 받아오는가의 차이)
	 */
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
		// id로 DB에서 업데이트할 직원을 찾아서 화면에 표시하기
		Employee employee = employeeService.findByEmployeeId(id); // DB에서 찾기
		System.out.println(employee);
		model.addAttribute("employee", employee);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	
}
