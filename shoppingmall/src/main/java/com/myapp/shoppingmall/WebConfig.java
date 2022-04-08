package com.myapp.shoppingmall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		// controller 대신 view를 매핑함
//		registry.addViewController("/").setViewName("home");
//	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 저장된 이미지파일을 불러올 경로 지정
		registry.addResourceHandler("/media/**")
				//.addResourceLocations("file:///C:/java502/SpringWorkspace/shoppingmall/src/main/resources/static/media/");	// 집컴
				.addResourceLocations("file:///C:/java502/kimuj/SpringWorkspace/shoppingmall/src/main/resources/static/media/");	// 학원컴
		
	}
}
