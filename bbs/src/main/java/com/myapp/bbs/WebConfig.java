package com.myapp.bbs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myapp.bbs.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터를 추가하는 메서드
		registry
			.addInterceptor(new LoginCheckInterceptor())	// 앞서 구현한 클래스를 사용
			.addPathPatterns("/board/**") 		// 인터셉터를 적용할 컨트롤러 주소 (/board로 시작하는 모든 주소)
			.excludePathPatterns("/board/list", "/board/get");		// 적용안할 주소(로그인 안해도 게시판 조회는 가능하도록)
	}
}
