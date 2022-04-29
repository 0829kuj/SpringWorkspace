package com.myapp.maybeCafe.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudyBoardVO {
	
	private int sno;		// 글번호
	
	private String writer;	// 작성자id
	
	private String title;	// 글제목
	
	private String content;	// 내용
	
	private LocalDateTime regdate;	// 작성일자
	
	private LocalDateTime updatedate;	// 수정일자
	
	private int hit;		// 조회수
	
	private int like_no;	// 추천수

}
