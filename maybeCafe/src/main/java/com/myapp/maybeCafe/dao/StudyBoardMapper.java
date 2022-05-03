package com.myapp.maybeCafe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.maybeCafe.model.StudyBoardVO;

@Mapper
public interface StudyBoardMapper {
	public List<StudyBoardVO> getStudyBoardList();	// 공부게시판 전체 게시글 가져오기
	public StudyBoardVO getPage(int sno);	// 공부게시판 단일 게시글 조회
	public void write(StudyBoardVO sBoardVO);	// 게시글 작성
	public void modify(StudyBoardVO sBoardVO);	// 게시글 수정
	public void delete(int sno);	// 게시글 삭제
}
