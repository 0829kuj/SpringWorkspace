package com.myapp.shoppingmall.ado;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entitys.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByName(String name);

	Product findBySlugAndIdNot(String slug, int id);
	
}
