package com.myapp.bbs;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.bbs.model.User;

@ControllerAdvice		// 모든 컨트롤러에 적용
public class Common {

	@ModelAttribute		// 모든 컨틀로러의 모델에 추가
	public void sharedData(Model model, HttpSession session) {
		// 세션에 인증된 유저가 있으면 유저이름을 모든 페이지에 전달
		User user = (User) session.getAttribute("user");
		if (user != null) {
			model.addAttribute("userName", user.getName());		// 유저객체가 있으면 userName을 저장
		}
	}
}
