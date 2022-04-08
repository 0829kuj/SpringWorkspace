package com.myapp.shoppingmall.ado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entitys.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String name);

	List<Category> findAllByOrderBySortingAsc();


}
