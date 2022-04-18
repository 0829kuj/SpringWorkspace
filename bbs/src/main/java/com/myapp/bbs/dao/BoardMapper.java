package com.myapp.bbs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.bbs.model.BoardVO;

@Mapper
public interface BoardMapper {
	
	public void enroll(BoardVO board);	// 게시글 등록
}
