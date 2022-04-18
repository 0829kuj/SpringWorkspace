package com.myapp.bbs.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;		// 게시글 번호
	
	private String title;	// 제목
	
	private String content;	// 내용
	
	private String writer;	// 글쓴이
	
	private LocalDateTime regdate;	// 등록날짜 timestemp에서 날짜시간을 가져오는 자바 날짜시간데이터
	
	private LocalDateTime updateDate;	// 수정날짜

}
