package com.myapp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myapp.pma.dto.EmployeeProject;
import com.myapp.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	// 자동으로 CRUD 객체 생성
	@Override
	List<Employee> findAll();
	
	// 쿼리문을 실행하여 결과를 리스트로 리턴함 (dto의 객체 EmployeeProject로 리턴)
	@Query(nativeQuery = true, value = 
			  "SELECT LAST_NAME AS lastName ,FIRST_NAME AS firstName , COUNT(PROJECT_ID) AS count "
			+ "FROM EMPLOYEE e "
			+ "LEFT JOIN PROJECT_EMPLOYEE pe "
			+ "ON e.EMPLOYEE_ID = pe.EMPLOYEE_ID "
			+ "GROUP BY LAST_NAME ,FIRST_NAME, e.EMPLOYEE_ID "
			+ "ORDER BY count DESC")
	public List<EmployeeProject> employeeProjects();
}
