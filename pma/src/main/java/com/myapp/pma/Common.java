package com.myapp.pma;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice	// 모든 컨트롤러에 적용 (즉, 모든 주소에 적용됨)
public class Common {
	// 각 컨트롤러가 화면(view)에 보내는 모델 객체에 적용 (각 화면에서 이동하는 데이터에 아래 데이터들도 얹어서 함께 이동한다고보면됨)
	@ModelAttribute
	public void sharedData(Model model, Principal principal) {

		// Principal은 시큐리티 인증 시 인증된 유저정보를 담고있는 객체이다
		if (principal != null) {
			model.addAttribute("principal", principal.getName());	// 인증 유저의 username을 전달
		}
	}
}
;