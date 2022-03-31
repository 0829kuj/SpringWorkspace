package com.myapp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.dto.EmployeeProject;
import com.myapp.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);	
	}

	public List<EmployeeProject> employeeProjects() {		
		return employeeRepository.employeeProjects();
	}

	public Employee findByEmployeeId(long id) {
		return employeeRepository.findByEmployeeId(id);
	}

	public void update(Employee employee) {
		// 실제 DB에서 업데이트할 직원객체를 불러옴
		Employee emp = employeeRepository.findByEmployeeId(employee.getEmployeeId());
		// 필요한 내용만 업데이트
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());		
		employeeRepository.save(emp);	// 수정된 직원객체 emp를 저장함
	}

	public void deleteEmployeeById(long id) {
		// 선태한 직원을 삭제하는 메서드
		employeeRepository.deleteById(id);
	}

}
