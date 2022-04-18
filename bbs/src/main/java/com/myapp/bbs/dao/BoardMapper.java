package com.myapp.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.bbs.model.BoardVO;

@Mapper
public interface BoardMapper {
	
	public void enroll(BoardVO board);	// 게시글 등록
	public List<BoardVO> getList();	// 게시글 목록
	public BoardVO getPage(int bno);	// 게시글 조회
	public int modify(BoardVO board);	// 게시글 수정
	public int delete(int bno);		// 게시글 삭제
}
