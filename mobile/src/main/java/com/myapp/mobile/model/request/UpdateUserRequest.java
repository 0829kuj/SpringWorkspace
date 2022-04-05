package com.myapp.mobile.model.request;

import javax.validation.constraints.Size;

public class UpdateUserRequest {
	// 유저 객체에서 email을 제외한 name만 업데이트
	@Size(min = 2, message = "이름의 길이는 2자 이상")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
