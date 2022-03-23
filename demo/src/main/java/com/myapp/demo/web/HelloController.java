package com.myapp.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/basic")
	public String sayHello() {
		return "<h3>헬로우 월드</h3>";
	}
	
	@GetMapping("/korean")
	public String translate() {
		return "<h2>안녕하세요</h2>";
	}

	@GetMapping("/japen")
	public String translate2() {
		return "<h2>곤니치와</h2>";
	}
	
	@GetMapping("/form")
	public String form() {
		return "<form method=\"post\" action=\"/hello/formpost\">\r\n"
				+ "    이름 : <input type=\"text\" name=\"name\"><br>\r\n"
				+ "    학번 : <input type=\"text\" name=\"id\"><br>\r\n"
				+ "    학과 : <input type=\"text\" name=\"dep\"><br>\r\n"
				+ "    <input type=\"submit\">\r\n"
				+ "</form>";
	}
	
	@PostMapping("/formpost")	// request parameter로 받은 "name"속성을 java로 String 속성 name으로 받음
	public String formpost(@RequestParam("name") String name,
							@RequestParam("id") String id,
							@RequestParam("dep") String dep) {
		return "당신의 이름은 " + name + " id는 " + id + " 학과는 " + dep + "입니다.";
	}
	
	@GetMapping("/orders/{id}")	// {id} 는 패스변수 (파라메터가 아님)
	public String order(@PathVariable String id) {
		return "주문 아이디는 " + id;
	}
	
	
}
