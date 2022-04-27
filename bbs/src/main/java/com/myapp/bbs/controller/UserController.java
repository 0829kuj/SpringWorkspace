package com.myapp.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.bbs.dao.UserMapper;
import com.myapp.bbs.model.User;

@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/register")
	public Object getUsersView(@ModelAttribute User user) {
		// register의 user객체를 model로 전달
		return "register";
	}
	
	@PostMapping("/register")
	public String postUser(User user, Model model, HttpSession session, RedirectAttributes attr) {
		// 클라이언트 단 또는 서버 단에서 데이터 유효성 체크를 적용하는 것을 권장
	
		User duplicatedUser = userMapper.selectByEmail(user.getEmail());	// 이메일이 같은 유저가 없으면 null
		if (duplicatedUser == null) {
			// 이메일 중복이 아니므로 가입처리
			userMapper.insert(user);
			attr.addFlashAttribute("message", "가입되었습니다.");
			return "redirect:/login";
		} else {
			// 이메일 중복이므로 메시지와 함께 리다이렉트로 돌아감
			attr.addFlashAttribute("message", "email 중복입니다.");
			return "redirect:/register";
		}		
	}
	
}
