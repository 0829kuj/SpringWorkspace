package com.myapp.maybeCafe.service;

import java.util.List;

import com.myapp.maybeCafe.model.PageVO;
import com.myapp.maybeCafe.model.StudyBoardVO;

public interface StudyBoardService {
	public List<StudyBoardVO> getStudyBoardList();	// 공부게시판 전체 게시글 가져오기

	public StudyBoardVO getPage(int sno);	// 게시글 조회
	
	public void write(StudyBoardVO sBoardVO);	// 게시글 작성
	
	public void modify(StudyBoardVO sBoardVO);	// 게시글 수정
	
	public void delete(int sno);	// 게시글 삭제
	
	public List<StudyBoardVO> getListPaging(PageVO page);	// 페이징 적용 게시글목록
	
	public int getTotal();		// 게시글 총 갯수
}