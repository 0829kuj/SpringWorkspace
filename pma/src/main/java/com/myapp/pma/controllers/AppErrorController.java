package com.myapp.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 에러 페이지 컨트롤러는 에러 컨트롤러를 구현해야 함
@Controller
public class AppErrorController implements ErrorController {
	
	// 에러 발생 시 주소가 /error로 들어옴
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		// 에러 상태 코드 확인
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(status);
		if(status != null) {	// 에러가 맞으면
			Integer statusCode = Integer.valueOf(status.toString()); // 403, 403, 500 식의 코드로 바꿈
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "errorpages/404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "errorpages/500";
			}
			else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				return "errorpages/403";
			}
		}
		// 위의 에러상태에 해당되지 않을 겨우 이동하는 페이지
		return "errorpages/error";
	}
}
