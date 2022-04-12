package com.myapp.shoppingmall.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data					// get set메서드 toString 자동생성
@AllArgsConstructor		// 모든 필드변수를 추가한 생성자 자동생성
@NoArgsConstructor		// 기본 생성자 자동생성
public class Cart {
	
	private int id;			// id
	private String name;	// 이름
	private String price;	// 가격
	private int quantity;	// 수량
	private String image;	// 이미지

}
