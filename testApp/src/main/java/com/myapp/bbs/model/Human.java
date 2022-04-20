package com.myapp.bbs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {
	
	private int idNum;		// 회원 일련번호
	private String id;		// 회원 아이디
	private String name;	// 회원 이름
}
