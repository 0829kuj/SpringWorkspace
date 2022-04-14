package com.myapp.bbs.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	
	private int bno;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private LocalDateTime regdate;
	
	private LocalDateTime updateDate;

}
