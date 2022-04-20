package com.myapp.bbs.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Comment {
	
	private int coNum;		// 방명록 일련번호
	private String content;	// 방명록 내용
	private int writer;		// 작성자 id
	private int target;		// 방명록 주인은 동명이인이 있을 수 있으니 이름이 아니라 회원일련번호로 관리
	private LocalDateTime regdate;	// 등록날짜 timestemp에서 날짜시간을 가져오는 자바 날짜시간데이터
}
