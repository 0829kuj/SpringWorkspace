package com.myapp.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.demo.domain.User;

@Controller
public class HomeController {
	// view와 controller사이의 데이터를 model을 통해 전달
	@GetMapping("/")
	public String home(Model model) {
		// "user"이름으로 User의 bean객체를 넣어서 "user" = new User();
		model.addAttribute("user", new User());
		// model을 통해 index.html페이지에 전달
		return "index";	// index라는 이름의 view를 찾음
	}
	
	@PostMapping("/create")	// User객체로 넘겨받기때문에 매개변수도 User객체로 받아야함
	public String processFormData(User user, RedirectAttributes attr) {
//		System.out.println(user.toString());
		attr.addFlashAttribute("user", user);
		return "redirect:/display";	// redirect가 새로운 요청을 하여 display페이지로 요청
	} // redirect로 보낼 경우 User객체가 전달이 안되므로 정보전달이 불가능함. => RedirectAttributes의 객체를 통해 전달 
	
	@GetMapping("/display")
	public String displayFormData(User user, RedirectAttributes attr) {
		return "result";
	}
	
}
