package com.myapp.demo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.demo.domain.Product;

@RestController
@RequestMapping("/user")	// 요청주소가 https://localhost:8080/user 로 시작하는 모든 url을 이 클래스로 받아온다
public class UserController {
	
	@GetMapping("{id}")
	public String test(@PathVariable("id") String id) {
		return "유저 아이디 : " + id;
	}
	
	@GetMapping("/{id}/contact")
	public String displayContent(@PathVariable("id") String userId, 
								@RequestParam(value = "phone", defaultValue = "핸드폰 없음") String phone) {
		return "유저 아이디: " + userId + ", 연락처: " + phone;
	}
	
	// 리스트를 리턴하기 => 제이슨
	@GetMapping("/{id}/items")
	public List<String> displayUserItems() {
		return Arrays.asList("가방", "노트북", "신발");
	}
	
	// 객체를 리턴하기 => 제이슨
	@GetMapping("/{id}/products")
	public List<Product> displayUserProducts() {
		return Arrays.asList(new Product(1, "모자", 5000), new Product(2, "신발", 12000), new Product(3, "가방", 18000));
	}
}
