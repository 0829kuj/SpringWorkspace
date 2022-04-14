package com.myapp.bbs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.bbs.model.BoardVO;

@Mapper
public interface BoardMapper {
	
	public void enroll(BoardVO board);
}
