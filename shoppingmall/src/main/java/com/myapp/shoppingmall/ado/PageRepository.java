package com.myapp.shoppingmall.ado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entitys.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	// JpaRepository상속 시 List<PAGE>로 리턴되는 findAll 등 여러 메서드가 추가됨.

	Page findById(Integer id);
	
}
