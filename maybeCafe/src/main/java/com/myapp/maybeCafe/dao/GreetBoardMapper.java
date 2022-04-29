package com.myapp.maybeCafe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.maybeCafe.model.GreetBoardVO;

@Mapper
public interface GreetBoardMapper {
	public List<GreetBoardVO> getGreetBoardList();	// 가입인사 모두 가져오기
	public void write(GreetBoardVO gBoardVO);	// 가입인사 작성
}
