package com.myapp.maybeCafe.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.maybeCafe.model.UserVO;
import com.myapp.maybeCafe.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistratinController {
	
	private UserService uService;

	public RegistratinController(UserService userService) {
		this.uService = userService;
	}
	
	@GetMapping
	public String registerGET(Model model) {
		model.addAttribute("user", new UserVO());
		return "register";
	}
	
	@PostMapping
	public String registerPOST(UserVO user, RedirectAttributes attr) {
		
		
		uService.save(user);		// DB에 유저객체 저장
		return "redirect:/login";	// 회원가입 완료시 로그인 페이지로
	}
}
