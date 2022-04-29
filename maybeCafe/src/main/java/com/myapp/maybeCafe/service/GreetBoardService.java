package com.myapp.maybeCafe.service;

import java.util.List;

import com.myapp.maybeCafe.model.GreetBoardVO;

public interface GreetBoardService {
	public List<GreetBoardVO> getGreetBoardList();	// 가입인사 모두 가져오기
	public void write(GreetBoardVO gBoardVO);	// 게시글 작성
}
