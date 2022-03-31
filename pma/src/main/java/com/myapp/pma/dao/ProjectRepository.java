package com.myapp.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.pma.entities.Project;

// JPA에서는 repository에 CRUD 가능한 인터페이스 CrudRepository를 상속받음

public interface ProjectRepository extends CrudRepository<Project, Long> {
	// CrudRepository에 이미 CRUD 메서드가 완성되어있음. => JPA 하이버네이트가 구현코드까지 모두 자동생성함
	@Override
	List<Project> findAll();	// 기존의 findAll()은 리턴타입이 Iterable<Project>이므로 수정

	Project findByProjectId(Long id);	// id가 있을수도 null일수도 있으므로 Long타입이어야함 (long은 null을 못받음)
	
}
