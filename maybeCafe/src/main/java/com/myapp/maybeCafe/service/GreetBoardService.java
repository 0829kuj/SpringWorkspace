package com.myapp.maybeCafe.service;

import java.util.List;

import com.myapp.maybeCafe.model.GreetBoardVO;
import com.myapp.maybeCafe.model.PageVO;

public interface GreetBoardService {
	public List<GreetBoardVO> getGreetBoardList();	// 가입인사 모두 가져오기
	public void write(GreetBoardVO gBoardVO);	// 가입인사 작성
	public void delete(int gno);				// 가입인사 삭제
	public int getTotal();	// 게시글 총 갯수
	public List<GreetBoardVO> getListPaging(PageVO page);	// 페이징을 적용한 전체 게시글 가져오기. pageNum과 amount를 입력받아 객체 page생성.
}
