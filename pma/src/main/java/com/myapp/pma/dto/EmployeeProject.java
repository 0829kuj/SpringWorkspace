package com.myapp.pma.dto;

public interface EmployeeProject {
	// 스프링이 자동으로 생성할 수 있게 get메서드만 작성함
	// set메서드는 필요없음. DB에서 쿼리 결과를 가져오기만 하면 됨.
	public String getLastName(); 	 //lastName
	public String getFirstName(); 	 //firstName
	public String getCount();	  	 //count

}
