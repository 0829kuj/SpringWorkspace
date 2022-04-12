package com.myapp.shoppingmall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entites.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String name);

	List<Category> findAllByOrderBySortingAsc();

	Category findBySlug(String slug);

	

}
