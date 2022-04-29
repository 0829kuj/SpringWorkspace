package com.myapp.maybeCafe.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GreetBoardVO {
	private int gno;
	private String content;
	private String writer;
	private LocalDateTime regdate;
}
