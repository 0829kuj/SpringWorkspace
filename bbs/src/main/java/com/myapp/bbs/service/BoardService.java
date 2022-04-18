package com.myapp.bbs.service;

import java.util.List;

import com.myapp.bbs.model.BoardVO;

public interface BoardService {
	
	public void enroll(BoardVO board);	// 게시글 등록
	public List<BoardVO> getList();	// 게시글 목록
	public BoardVO getPage(int bno);	// 게시글 조회
	public int modify(BoardVO board);	// 게시글 수정
	public int delete(int bno);		// 게시글 삭제
}
