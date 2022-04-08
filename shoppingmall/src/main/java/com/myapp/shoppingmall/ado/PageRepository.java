package com.myapp.shoppingmall.ado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entitys.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	// JpaRepository상속 시 List<PAGE>로 리턴되는 findAll 등 여러 메서드가 추가됨.
	
	Page findBySlug(String slug);	// 실제 구현은 jpa 하이버네이트가 함

	Page findBySlugAndIdNot(String slug, int id);	// slug를 찾되 현재 id의 slug는 제외

	List<Page> findAllByOrderBySortingAsc();

	
}
