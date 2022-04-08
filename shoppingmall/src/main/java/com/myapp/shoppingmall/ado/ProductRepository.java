package com.myapp.shoppingmall.ado;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entitys.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByName(String name);

	Product findBySlugAndIdNot(String slug, int id);

	List<Product> findAllByCategoryId(String categoryId, Pageable pageable);

	long countByCategoryId(String categoryId);
	
}
