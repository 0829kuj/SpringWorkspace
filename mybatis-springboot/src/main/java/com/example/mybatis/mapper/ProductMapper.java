package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.model.Product;

@Mapper
public interface ProductMapper {
	// 구현이 안된 추상메서드들을 연결된 mapper폴더의 ProductMapper.xml에서 작성함.
    Product selectProductById(Long id); 	//id로 제품을 검색
    List<Product> selectAllProducts();		//모든 제품 리스트
    void insertProduct(Product product);	// 새 제품 생성
    void updateProduct(Product product);	// 제품 수정
    void deleteProductById(Long id);		// id로 제품 삭제 

}
