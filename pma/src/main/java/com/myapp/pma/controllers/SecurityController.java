package com.myapp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.pma.dao.UserAccountRepository;
import com.myapp.pma.entities.UserAccount;

@Controller
public class SecurityController {
	// 암호화는 유저를 저장할때, 유저를 인증할 때 모두 필요함.
	@Autowired
	UserAccountRepository userRepo;	// 검사할때 암호화를 위한 객체
	
	@Autowired
	BCryptPasswordEncoder bEncoder; // 인증할때 암호화를 위한 객체
	
	// 가입하기 화면 표시
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();	// 빈 유저 객체 생성
		model.addAttribute("userAccount", userAccount); // 아래 화면에 매핑함 => 내용을 입력하면 객체로 받으면 됨
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(bEncoder.encode(user.getPassword())); // 인코더를 사용해 비밀번호를 암호화하여 저장
//		user.setRole("ROLE_USER");
//		user.setEnabled(true);
		userRepo.save(user);

		return "redirect:/";
	}
}
