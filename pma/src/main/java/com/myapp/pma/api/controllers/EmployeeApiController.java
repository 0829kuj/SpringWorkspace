package com.myapp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
//	@GetMapping
//	public Iterable<Employee> getEmployees(){
//		return empRepo.findAll();
//	}
	
	// 페이징을 적용한 직원 리스트
	@GetMapping
	@ResponseStatus(HttpStatus.OK)				// page는 0페이지부터 시작임
	public Iterable<Employee> findPaginatedEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
													@RequestParam(value = "size", defaultValue = "5") int size) {
		// 페이지설정 리파지토리를 변경(상속받는 클래스를 변경)
		Pageable pageable = PageRequest.of(page, size);
		return empRepo.findAll(pageable);
	}
	
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id){
		return empRepo.findByEmployeeId(id);
	}
	// 요청하는 body에 json타입의 새 직원 데이터를 입력 시 새 직원 생성 후 그 직원을 리턴
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)		// 상태 201 생성
	public Employee create(@RequestBody Employee employee){
		return empRepo.save(employee);
	}
	
	// 전체 업데이트 http put 메서드
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)	// http 상태 메시지
	public Employee update(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee);	// save는 id가 있으면 업데이트 없으면 새로 생성
	}
	
	// 부분 업데이트시 업데이트할 직원의 id가 필요
	@PutMapping(path = "/{id}", consumes = "application/json")
	public Employee update(@PathVariable long id, @RequestBody Employee employee) {
		Employee emp = empRepo.findByEmployeeId(id);	// 업데이트 전 원래 데이터를 불러옴
		// null값이 아니면 부분업데이트
		if(employee.getEmail() != null) {
			emp.setEmail(employee.getEmail());
		}
		if(employee.getFirstName() != null) {
			emp.setFirstName(employee.getFirstName());
		}
		if(employee.getLastName() != null) {
			emp.setLastName(employee.getLastName());
		}
		return empRepo.save(emp);	// 업데이트된 emp를 저장
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		// 예외가 발생하여 데이터가 삭제되지 않았을 경우를 알기위해 try/catch문 사용
		try {
			empRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("삭제 안됨");	
		}
	}
	
}